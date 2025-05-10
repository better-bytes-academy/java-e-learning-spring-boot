package com.example.onlinelearning.repository;

import com.example.onlinelearning.entity.Enrollment;
import com.example.onlinelearning.util.EnrollmentId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, EnrollmentId> {

}
