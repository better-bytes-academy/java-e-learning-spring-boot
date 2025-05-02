package com.example.onlinelearning.service;

import com.example.onlinelearning.dto.Comment;
import com.example.onlinelearning.entity.ForumComment;
import com.example.onlinelearning.entity.User;
import com.example.onlinelearning.repository.DiscussionRepository;
import com.example.onlinelearning.repository.UserRepository;
import com.example.onlinelearning.response.CommentsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DiscussionService {
    @Autowired
    private DiscussionRepository discussionRepository;

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<ForumComment> discussionCreate(UserDetails userDetails, ForumComment forumComment) {
        User user = userRepository.findByEmail(userDetails.getUsername()).get();
        Integer userId = user.getUser_id();

        forumComment.setUserId(userId);

        return ResponseEntity.ok(discussionRepository.save(forumComment));
    }

    public List<CommentsResponse> comments(UserDetails userDetails, Map<String, Object> mapDiscussionId) {
        Integer discussionId = (Integer) mapDiscussionId.get("discussionId");

        List<CommentsResponse> listComments = new ArrayList<>();
        List<ForumComment> listCommentsRoot = discussionRepository.selectCommentRoot(discussionId);

        for(int i = 0 ; i < listCommentsRoot.size() ; i++){
            CommentsResponse temp = new CommentsResponse();
            ForumComment forumComment = listCommentsRoot.get(i);

            Comment comment = new Comment();
            comment.setCommentId(forumComment.getCommentId());
            comment.setParentCommentId(forumComment.getParentCommentId());
            comment.setParentContent(discussionRepository.selectCommentById(forumComment.getParentCommentId()));
            comment.setRootCommentId(forumComment.getRootCommentId());
            comment.setEmail(discussionRepository.selectEmailByCommentId(forumComment.getCommentId()));
            comment.setContent(forumComment.getContent());
            comment.setCreateAt(forumComment.getCreatedAt());

            temp.setComment(comment); // đã set thuộc tính 1

            List<ForumComment> listReply = discussionRepository.selectCommentReplies(comment.getCommentId());
            for(int j = 0 ; j < listReply.size() ; j++){
                ForumComment gan = listReply.get(j);

                Comment comment1 = new Comment();
                comment1.setCommentId(gan.getCommentId());
                comment1.setParentCommentId(gan.getParentCommentId());
                comment1.setParentContent(discussionRepository.selectCommentById(gan.getParentCommentId()));
                comment1.setRootCommentId(gan.getRootCommentId());
                comment1.setEmail(discussionRepository.selectEmailByCommentId(gan.getCommentId()));
                comment1.setContent(gan.getContent());
                comment1.setCreateAt(gan.getCreatedAt());

                temp.getReplies().add(comment1);
            }

            listComments.add(temp);
        }
        return listComments;
    }
}
