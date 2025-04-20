package com.example.onlinelearning.repository;

import com.example.onlinelearning.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Integer> {
    public Lesson findByLessonId(Integer lessonId);

}
