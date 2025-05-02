package com.example.onlinelearning.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class Comment implements Serializable {
    private Integer commentId;
    private Integer parentCommentId;
    private String parentContent;
    private Integer rootCommentId;
    private String email;

    private String content;
    private Timestamp createAt;

    public Comment() {
    }

    public Comment(Integer commentId, Integer parentCommentId, String parentContent, Integer rootCommentId, String email,String content, Timestamp createAt) {
        this.commentId = commentId;
        this.parentCommentId = parentCommentId;
        this.parentContent = parentContent;
        this.rootCommentId = rootCommentId;
        this.email = email;
        this.content = content;
        this.createAt = createAt;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(Integer parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    public String getParentContent() {
        return parentContent;
    }

    public void setParentContent(String parentContent) {
        this.parentContent = parentContent;
    }

    public Integer getRootCommentId() {
        return rootCommentId;
    }

    public void setRootCommentId(Integer rootCommentId) {
        this.rootCommentId = rootCommentId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
