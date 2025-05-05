package com.example.onlinelearning.service;

import com.example.onlinelearning.entity.Courses;
import com.example.onlinelearning.entity.Enrollment;
import com.example.onlinelearning.entity.ForumDis;
import com.example.onlinelearning.entity.User;
import com.example.onlinelearning.repository.CoursesRepository;
import com.example.onlinelearning.repository.EnrollmentRepository;
import com.example.onlinelearning.repository.ForumDisRepository;
import com.example.onlinelearning.repository.UserRepository;
import com.example.onlinelearning.request.UpdateCourses;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    @Autowired
    private CoursesRepository coursesRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private ForumDisRepository forumDisRepository;

    @Transactional
    public Courses create(UserDetails userDetails, Courses courses){
        User user = userRepository.findByEmail(userDetails.getUsername()).get();
        courses.setCreator_id(user.getUser_id());
        coursesRepository.save(courses);

        ForumDis forumDis = new ForumDis();
        forumDis.setCourseId(courses.getId());
        forumDis.setUserId(user.getUser_id());
        forumDisRepository.save(forumDis);

        return coursesRepository.findById(courses.getId()).get();
    }

    @Transactional
    public Courses update(UpdateCourses updateCourses, Integer coursesId){
        Courses courses = coursesRepository.findById(coursesId).get();
        courses.setTitle(updateCourses.getTitle());
        courses.setDescription(updateCourses.getDescription());
        courses.setCategory(updateCourses.getCategory());
        courses.setLevel(updateCourses.getLevel());
        coursesRepository.save(courses);

        return courses;
    }

    @Transactional
    public void delete(Integer coursesId) {
        coursesRepository.deleteById(coursesId);
    }

    @Transactional
    public Enrollment enrollment_method(UserDetails userDetails,Enrollment enrollment){
        User user = userRepository.findByEmail(userDetails.getUsername()).get();
        Integer userId = user.getUser_id();

        if(enrollmentRepository.findByUserIdAndCourseId(userId,enrollment.getCourseId()) != null){
            throw new IllegalArgumentException("You have registered for this course.");
        }

        enrollmentRepository.save(enrollment);
        return enrollment;
    }
}
