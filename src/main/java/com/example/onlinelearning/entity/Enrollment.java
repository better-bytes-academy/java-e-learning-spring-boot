package com.example.onlinelearning.entity;

import com.example.onlinelearning.util.EnrollmentId;
import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "enrollment")
@IdClass(EnrollmentId.class)
public class Enrollment implements Serializable {
    @Id
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Id
    @Column(name = "course_id", nullable = false)
    private Integer courseId;

    @Column(name = "enrolled_at", nullable = false)
    private Timestamp enrolledAt = new Timestamp(System.currentTimeMillis());

    public Enrollment() {
    }

    public Enrollment(Integer userId, Integer courseId, Timestamp enrolledAt) {
        this.userId = userId;
        this.courseId = courseId;
        this.enrolledAt = enrolledAt;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Timestamp getEnrolledAt() {
        return enrolledAt;
    }

    public void setEnrolledAt(Timestamp enrolledAt) {
        this.enrolledAt = enrolledAt;
    }
}
