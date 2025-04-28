package com.example.onlinelearning.request;

import com.example.onlinelearning.entity.AssessmentAnswer;
import com.example.onlinelearning.entity.AssessmentQuestion;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.aspectj.weaver.patterns.TypePatternQuestions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class QuestionAndAnswerRequest implements Serializable {
    private AssessmentQuestion question;
    private List<AssessmentAnswer> listAnswer = new ArrayList<>();

    public QuestionAndAnswerRequest() {
    }

    public QuestionAndAnswerRequest(AssessmentQuestion question, List<AssessmentAnswer> listAnswer) {
        this.question = question;
        this.listAnswer = listAnswer;
    }

    public AssessmentQuestion getQuestion() {
        return question;
    }

    public void setQuestion(AssessmentQuestion question) {
        this.question = question;
    }

    public List<AssessmentAnswer> getListAnswer() {
        return listAnswer;
    }

    public void setListAnswer(List<AssessmentAnswer> listAnswer) {
        this.listAnswer = listAnswer;
    }
}
