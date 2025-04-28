package com.example.onlinelearning.response;

import com.example.onlinelearning.entity.UserAnswer;
import com.example.onlinelearning.entity.UserSubmission;

import java.util.ArrayList;
import java.util.List;

public class ExamResultResponse {
    private UserSubmission userSubmission;
    private List<UserAnswer> listUserAnswer = new ArrayList<>();

    public ExamResultResponse() {
    }

    public ExamResultResponse(UserSubmission userSubmission, List<UserAnswer> listUserAnswer) {
        this.userSubmission = userSubmission;
        this.listUserAnswer = listUserAnswer;
    }

    public UserSubmission getUserSubmission() {
        return userSubmission;
    }

    public void setUserSubmission(UserSubmission userSubmission) {
        this.userSubmission = userSubmission;
    }

    public List<UserAnswer> getListUserAnswer() {
        return listUserAnswer;
    }

    public void setListUserAnswer(List<UserAnswer> listUserAnswer) {
        this.listUserAnswer = listUserAnswer;
    }
}
