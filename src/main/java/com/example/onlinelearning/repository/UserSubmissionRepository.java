package com.example.onlinelearning.repository;

import com.example.onlinelearning.entity.UserSubmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSubmissionRepository extends JpaRepository<UserSubmission,Integer> {
    @Query("select count(us) from UserSubmission us where us.userId = :userId and us.assessmentId = :assessmentId")
    long countSubmissionsOfUserForAssessment(@Param("userId") int userId, @Param("assessmentId") int assessmentId);
}
