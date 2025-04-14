package com.example.onlinelearning.util;

import java.io.Serializable;
import java.util.Objects;

public class EnrollmentId implements Serializable {
    private Integer userId;
    private Integer courseId;

    public EnrollmentId() {}

    public EnrollmentId(Integer userId, Integer courseId) {
        this.userId = userId;
        this.courseId = courseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnrollmentId that = (EnrollmentId) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(courseId, that.courseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, courseId);
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }
}
