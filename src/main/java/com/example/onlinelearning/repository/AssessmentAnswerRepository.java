package com.example.onlinelearning.repository;

import com.example.onlinelearning.entity.AssessmentAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AssessmentAnswerRepository extends JpaRepository<AssessmentAnswer, Integer> {
    @Query("select aa from AssessmentAnswer aa where aa.questionId = :questionId and aa.isCorrect = true")
    AssessmentAnswer getCorrectAnswer(@Param("questionId") Integer questionId);
}
