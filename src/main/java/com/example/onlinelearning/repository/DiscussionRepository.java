package com.example.onlinelearning.repository;

import com.example.onlinelearning.entity.ForumComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscussionRepository extends JpaRepository<ForumComment, Integer> {
//    @Query("from ForumComment fc where fc.discussionId = :discussionId order by fc.createdAt asc ")
//    List<ForumComment> selectAllCommentOfDiscussion(@Param("discussionId") Integer discussionId);

    @Query("from ForumComment fc where fc.discussionId = :discussionId and fc.parentCommentId is null order by fc.createdAt asc ")
    List<ForumComment> selectCommentRoot(@Param("discussionId") Integer discussionId);

    @Query("from ForumComment fc where fc.rootCommentId = :rootCommentId order by fc.createdAt asc ")
    List<ForumComment> selectCommentReplies(@Param("rootCommentId") Integer rootCommentId);

    @Query("select fc.content from ForumComment fc where fc.commentId = :commentId")
    String selectCommentById(@Param("commentId") Integer commentId);

    @Query("select user.email from ForumComment fc join User user on fc.userId = user.user_id where fc.commentId = :commentId")
    String selectEmailByCommentId(@Param("commentId") Integer commentId);
}
