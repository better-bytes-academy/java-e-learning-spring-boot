package com.example.onlinelearning.request;

import java.io.Serializable;

public class ExamResultRequest implements Serializable {
    private Integer assessmentId;
    private Integer attemptCount;

    public ExamResultRequest() {
    }

    public ExamResultRequest( Integer assessmentId, Integer attemptCount) {
        this.assessmentId = assessmentId;
        this.attemptCount = attemptCount;
    }


    public Integer getAssessmentId() {
        return assessmentId;
    }

    public void setAssessmentId(Integer assessmentId) {
        this.assessmentId = assessmentId;
    }

    public Integer getAttemptCount() {
        return attemptCount;
    }

    public void setAttemptCount(Integer attemptCount) {
        this.attemptCount = attemptCount;
    }
}
