package com.example.onlinelearning.response;

import com.example.onlinelearning.dto.Comment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CommentsResponse implements Serializable {
    private Comment comment;
    private List<Comment> replies = new ArrayList<>();

    public CommentsResponse() {
    }

    public CommentsResponse(Comment comment, List<Comment> replies) {
        this.comment = comment;
        this.replies = replies;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public List<Comment> getReplies() {
        return replies;
    }

    public void setReplies(List<Comment> replies) {
        this.replies = replies;
    }
}
