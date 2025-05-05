package com.example.onlinelearning.repository;

import com.example.onlinelearning.entity.Enrollment;
import com.example.onlinelearning.util.EnrollmentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, EnrollmentId> {
    @Query("select e.userId from Enrollment e where e.courseId = :courseId")
    List<Integer> selectListUserIdByCourseId(@Param("courseId") Integer courseId);

    Enrollment findByUserIdAndCourseId(Integer userId, Integer courseId);
}
