package com.example.onlinelearning.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "assessment")
public class Assessment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assessment_id")
    private Integer assessmentId;

    @Column(name = "course_id", nullable = false)
    private Integer courseId;

    @Column(name = "teacher_id", nullable = false)
    private  Integer teacherId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "total_marks", nullable = false)
    private Integer totalMarks = 10;

    @Column(name = "time_limit")
    private Integer timeLimit;

    @Column(name = "attempt_limit")
    private Integer attemptLimit = 1;

    @Column(name = "created_at")
    private Timestamp createdAt = new Timestamp(System.currentTimeMillis());

    @Column(name = "due_date")
    private LocalDateTime dueDate;

    public Assessment() {
    }

    public Assessment(Integer assessmentId, Integer courseId, Integer teacherId, String title, String description, Integer totalMarks, Integer timeLimit, Integer attemptLimit, Timestamp createdAt, LocalDateTime dueDate) {
        this.assessmentId = assessmentId;
        this.courseId = courseId;
        this.teacherId = teacherId;
        this.title = title;
        this.description = description;
        this.totalMarks = totalMarks;
        this.timeLimit = timeLimit;
        this.attemptLimit = attemptLimit;
        this.createdAt = createdAt;
        this.dueDate = dueDate;
    }

    public Integer getAssessmentId() {
        return assessmentId;
    }

    public void setAssessmentId(Integer assessmentId) {
        this.assessmentId = assessmentId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(Integer totalMarks) {
        this.totalMarks = totalMarks;
    }

    public Integer getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Integer timeLimit) {
        this.timeLimit = timeLimit;
    }

    public Integer getAttemptLimit() {
        return attemptLimit;
    }

    public void setAttemptLimit(Integer attemptLimit) {
        this.attemptLimit = attemptLimit;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }
}
