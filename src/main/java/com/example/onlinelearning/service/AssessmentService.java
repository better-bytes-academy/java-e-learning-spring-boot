package com.example.onlinelearning.service;

import com.example.onlinelearning.entity.*;
import com.example.onlinelearning.repository.*;
import com.example.onlinelearning.request.ListAnswerStudentRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class AssessmentService {
    @Autowired
    private AssessmentRepository assessmentRepository;

    @Autowired
    private AssessmentQuestionRepository assessmentQuestionRepository;

    @Autowired
    private AssessmentAnswerRepository assessmentAnswerRepository;

    @Autowired
    private UserAnswerRepository userAnswerRepository;

    @Autowired
    private UserSubmissionRepository userSubmissionRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public ResponseEntity<?> assessmentCreate(UserDetails userDetails, Assessment assessment){
        User user = userRepository.findByEmail(userDetails.getUsername()).get();
        Integer userId = user.getUser_id();

        assessment.setTeacherId(userId);
        if(assessment.getTimeLimit() == null){
            assessment.setDueDate(null);
        }
        else{
            assessment.setDueDate(assessment.getCreatedAt().toLocalDateTime().plusMinutes(assessment.getTimeLimit()));
        }

        assessmentRepository.save(assessment);
////////////////////////////////////////////////////////////////////////////

        BigDecimal totalMarks = BigDecimal.valueOf(assessment.getTotalMarks());
        BigDecimal numberOfQuestions = BigDecimal.valueOf(assessment.getListQuestion().size());

        BigDecimal point = totalMarks.divide(numberOfQuestions, 2, RoundingMode.HALF_UP); // điểm của 1 câu

        for(int i = 0 ; i < assessment.getListQuestion().size() ; i++){
            AssessmentQuestion qTemp = assessment.getListQuestion().get(i).getQuestion();
            qTemp.setAssessmentId(assessment.getAssessmentId());
            qTemp.setMark(point);

            assessmentQuestionRepository.save(qTemp);

            for(int j = 0 ; j < assessment.getListQuestion().get(i).getListAnswer().size() ; j++){
//                AnswerRequest answerRequest = assessment.getListQuestion().get(i).getListAnswer().get(j);
//                AssessmentAnswer aTemp = new AssessmentAnswer();
//
//                aTemp.setQuestionId(qTemp.getQuestionId());
//                aTemp.setAnswerText(answerRequest.getAnswerText());
//                aTemp.setIsCorrect(answerRequest.getIsCorrect());

                AssessmentAnswer aTemp = assessment.getListQuestion().get(i).getListAnswer().get(j);
                aTemp.setQuestionId(qTemp.getQuestionId());

                assessmentAnswerRepository.save(aTemp);
            }
        }

        return ResponseEntity.ok(assessment);
    }

    @Transactional
    public ResponseEntity<?> assessmentStart(UserDetails userDetails, ListAnswerStudentRequest listAnswerStudentRequest){
        User user = userRepository.findByEmail(userDetails.getUsername()).get();
        Integer userId = user.getUser_id(); // id học sinh

        Integer attemptLimit = assessmentRepository.findByAssessmentId(listAnswerStudentRequest.getAssessmentId()).getAttemptLimit();
        Integer countSubmissionsOfUserForAssessment = (int) userSubmissionRepository.countSubmissionsOfUserForAssessment(userId,listAnswerStudentRequest.getAssessmentId()) + 1;

        if( attemptLimit != null){
            if(countSubmissionsOfUserForAssessment > attemptLimit) {
                throw new IllegalArgumentException("Cannot post, completed several times");
            }
        }

        BigDecimal mark = assessmentQuestionRepository.findByAssessmentId(listAnswerStudentRequest.getAssessmentId()).get(0).getMark();
        Integer correctAnswerCount = 0;

        for(int i = 0 ; i < listAnswerStudentRequest.getListUserAnswer().size() ; i++){
            UserAnswer userAnswer = listAnswerStudentRequest.getListUserAnswer().get(i);
            if(userAnswer.getSelectedOptionId().equals(assessmentAnswerRepository.getCorrectAnswer(userAnswer.getQuestionId()).getAnswerId())  && userAnswer.getSelectedOptionId() != null){
                correctAnswerCount++;
            }
        } // đếm số câu trả  lời đúng

        //tính tổng điểm
        BigDecimal totalScore = mark.multiply(BigDecimal.valueOf(correctAnswerCount));

        UserSubmission userSubmission = new UserSubmission();

        userSubmission.setUserId(userId);
        userSubmission.setAssessmentId(listAnswerStudentRequest.getAssessmentId());
        userSubmission.setScore(totalScore);

        userSubmissionRepository.save(userSubmission);

        for(int i = 0 ; i < listAnswerStudentRequest.getListUserAnswer().size() ; i++){
            UserAnswer userAnswer = listAnswerStudentRequest.getListUserAnswer().get(i);
            userAnswer.setSubmissionId(userSubmission.getSubmissionId());
            userAnswerRepository.save(userAnswer);
        }

        return ResponseEntity.ok(userSubmission);
    }
}
