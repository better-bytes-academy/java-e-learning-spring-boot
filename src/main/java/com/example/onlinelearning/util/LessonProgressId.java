package com.example.onlinelearning.util;

import java.io.Serializable;
import java.util.Objects;

public class LessonProgressId implements Serializable {
    private Integer userId;
    private Integer lessonId;

    public LessonProgressId() {
    }

    public LessonProgressId(Integer userId, Integer lessonId) {
        this.userId = userId;
        this.lessonId = lessonId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LessonProgressId that = (LessonProgressId) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(lessonId, that.lessonId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, lessonId);
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
}
