package com.example.onlinelearning.repository;

import com.example.onlinelearning.entity.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoursesRepository extends JpaRepository<Courses, Integer> {
    public Optional<Courses> findById(Integer id);

}
