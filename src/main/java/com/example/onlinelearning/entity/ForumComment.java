package com.example.onlinelearning.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "forum_comment")
public class ForumComment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Integer commentId;

    @Column(name = "discussion_id", nullable = false)
    private Integer discussionId;

    @Column(name = "parent_comment_id")
    private Integer parentCommentId;

    @Column(name = "root_comment_id")
    private Integer rootCommentId;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "content", columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(name = "created_at")
    private Timestamp createdAt = new Timestamp(System.currentTimeMillis());

    public ForumComment() {
    }

    public ForumComment(Integer commentId, Integer discussionId, Integer parentCommentId, Integer rootCommentId, Integer userId, String content, Timestamp createdAt) {
        this.commentId = commentId;
        this.discussionId = discussionId;
        this.parentCommentId = parentCommentId;
        this.rootCommentId = rootCommentId;
        this.userId = userId;
        this.content = content;
        this.createdAt = createdAt;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getDiscussionId() {
        return discussionId;
    }

    public void setDiscussionId(Integer discussionId) {
        this.discussionId = discussionId;
    }

    public Integer getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(Integer parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getRootCommentId() {
        return rootCommentId;
    }

    public void setRootCommentId(Integer rootCommentId) {
        this.rootCommentId = rootCommentId;
    }
}
