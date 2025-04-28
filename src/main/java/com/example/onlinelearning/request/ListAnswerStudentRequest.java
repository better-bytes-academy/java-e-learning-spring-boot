package com.example.onlinelearning.request;

import com.example.onlinelearning.entity.UserAnswer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListAnswerStudentRequest implements Serializable {
    private Integer assessmentId;
    private List<UserAnswer> listUserAnswer = new ArrayList<>();

    public ListAnswerStudentRequest() {
    }

    public ListAnswerStudentRequest(Integer assessmentId, List<UserAnswer> listUserAnswer) {
        this.assessmentId = assessmentId;
        this.listUserAnswer = listUserAnswer;
    }

    public Integer getAssessmentId() {
        return assessmentId;
    }

    public void setAssessmentId(Integer assessmentId) {
        this.assessmentId = assessmentId;
    }

    public List<UserAnswer> getListUserAnswer() {
        return listUserAnswer;
    }

    public void setListUserAnswer(List<UserAnswer> listUserAnswer) {
        this.listUserAnswer = listUserAnswer;
    }
}
