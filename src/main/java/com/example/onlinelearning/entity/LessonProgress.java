package com.example.onlinelearning.entity;

import com.example.onlinelearning.util.LessonProgressId;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "lesson_progress")
@IdClass(LessonProgressId.class)
public class LessonProgress implements Serializable {
    @Id
    @Column(name = "user_id")
    private Integer userId;

    @Id
    @Column(name = "lesson_id")
    private Integer lessonId;

    @Column(name = "completed")
    private Boolean completed;

    public LessonProgress() {
    }

    public LessonProgress(Integer userId, Integer lessonId, Boolean completed) {
        this.userId = userId;
        this.lessonId = lessonId;
        this.completed = completed;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getLessonId() {
        return lessonId;
    }

    public void setLessonId(Integer lessonId) {
        this.lessonId = lessonId;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
}
