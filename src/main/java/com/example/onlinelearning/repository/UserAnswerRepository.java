package com.example.onlinelearning.repository;

import com.example.onlinelearning.entity.UserAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAnswerRepository extends JpaRepository<UserAnswer, Integer> {
    List<UserAnswer> findBySubmissionId(Integer SubmissionId);
}
