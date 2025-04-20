package com.example.onlinelearning.repository;

import com.example.onlinelearning.entity.*;
import com.example.onlinelearning.util.LessonProgressId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonProgressRepository extends JpaRepository<LessonProgress, LessonProgressId> {

    @Query("select count(lp) from Courses cour " +
            "join Chapter chap on cour.id = chap.courseId and cour.id = :courseId " +
            "join Lesson less on chap.chapterId = less.chapterId " +
            "join LessonProgress lp on less.lessonId = lp.lessonId and lp.userId = :userId ")
    public Long countLessonCompleted(@Param("courseId") Integer courseId, @Param("userId") Integer userId );


    @Query("select count(cour) from Courses cour " +
            "join Chapter chap on cour.id = chap.courseId and cour.id = :courseId " +
            "join Lesson less on chap.chapterId = less.chapterId")
    public Long countAllLessonOfCourse(@Param("courseId") Integer courseId);
}
