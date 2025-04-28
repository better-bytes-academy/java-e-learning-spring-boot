package com.example.onlinelearning.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "user_submission")
public class UserSubmission implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "submission_id")
    private Integer submissionId;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "assessment_id", nullable = false)
    private Integer assessmentId;

    @Column(name = "score", nullable = false, precision = 5, scale = 2)
    private BigDecimal score;

    @Column(name = "submitted_at")
    private Timestamp submittedAt = new Timestamp(System.currentTimeMillis());

    public UserSubmission() {
    }

    public UserSubmission(Integer submissionId, Integer userId, Integer assessmentId, BigDecimal score, Timestamp submittedAt) {
        this.submissionId = submissionId;
        this.userId = userId;
        this.assessmentId = assessmentId;
        this.score = score;
        this.submittedAt = submittedAt;
    }

    public Integer getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(Integer submissionId) {
        this.submissionId = submissionId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAssessmentId() {
        return assessmentId;
    }

    public void setAssessmentId(Integer assessmentId) {
        this.assessmentId = assessmentId;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public Timestamp getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(Timestamp submittedAt) {
        this.submittedAt = submittedAt;
    }
}
