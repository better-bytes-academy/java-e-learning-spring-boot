package com.example.onlinelearning.service;

import com.example.onlinelearning.entity.Assessment;
import com.example.onlinelearning.entity.User;
import com.example.onlinelearning.repository.AssessmentRepository;
import com.example.onlinelearning.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AssessmentService {
    @Autowired
    private AssessmentRepository assessmentRepository;

    @Autowired
    private UserRepository userRepository;

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

        return ResponseEntity.ok(assessment);
    }
}
