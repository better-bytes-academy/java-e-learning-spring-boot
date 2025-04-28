package com.example.onlinelearning.entity;

import jakarta.persistence.*;

import java.io.Serializable;
@Entity
@Table(name = "user_answer")
public class UserAnswer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id")
    private Integer answerId;

    @Column(name = "submission_id", nullable = false)
    private Integer submissionId;

    @Column(name = "question_id", nullable = false)
    private Integer questionId;

    @Column(name = "selected_option_id")
    private Integer selectedOptionId;

    public UserAnswer() {
    }

    public UserAnswer(Integer answerId, Integer submissionId, Integer questionId, Integer selectedOptionId) {
        this.answerId = answerId;
        this.submissionId = submissionId;
        this.questionId = questionId;
        this.selectedOptionId = selectedOptionId;
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public Integer getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(Integer submissionId) {
        this.submissionId = submissionId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getSelectedOptionId() {
        return selectedOptionId;
    }

    public void setSelectedOptionId(Integer selectedOptionId) {
        this.selectedOptionId = selectedOptionId;
    }
}
