package com.example.onlinelearning.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "forum_dis")
public class ForumDis implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "discussion_id")
    private Integer discussionId;

    @Column(name = "course_id", nullable = false)
    private Integer courseId;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "created_at")
    private Timestamp createdAt = new Timestamp(System.currentTimeMillis());

    public ForumDis() {
    }

    public ForumDis(Integer discussionId, Integer courseId, Integer userId, Timestamp created_at) {
        this.discussionId = discussionId;
        this.courseId = courseId;
        this.userId = userId;
        this.createdAt = created_at;
    }

    public Integer getDiscussionId() {
        return discussionId;
    }

    public void setDiscussionId(Integer discussionId) {
        this.discussionId = discussionId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
