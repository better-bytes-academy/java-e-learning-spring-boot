package com.example.onlinelearning.controller;

import com.example.onlinelearning.entity.ForumComment;
import com.example.onlinelearning.response.CommentsResponse;
import com.example.onlinelearning.service.DiscussionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/discussion")
public class DiscussionController {
    @Autowired
    private DiscussionService discussionService;

    @PostMapping("/create")
    public ResponseEntity<ForumComment> discussionCreate(@AuthenticationPrincipal UserDetails userDetails, @RequestBody ForumComment forumComment){
        return discussionService.discussionCreate(userDetails, forumComment);
    }

    @PostMapping("/comments")
    public ResponseEntity<List<CommentsResponse>> comments(@AuthenticationPrincipal UserDetails userDetails, @RequestBody Map<String, Object> mapDiscussionId){
        return ResponseEntity.ok(discussionService.comments(userDetails, mapDiscussionId));
    }
}
