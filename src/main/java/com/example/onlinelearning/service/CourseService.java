package com.example.onlinelearning.service;

import com.example.onlinelearning.entity.Courses;
import com.example.onlinelearning.entity.Enrollment;
import com.example.onlinelearning.entity.ForumDis;
import com.example.onlinelearning.entity.User;
import com.example.onlinelearning.repository.*;
import com.example.onlinelearning.request.UpdateCourses;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

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

    @Autowired
    private CoursesRepositoryImp coursesRepositoryImp;

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
    public Courses update(UserDetails userDetails,UpdateCourses updateCourses, Integer coursesId){
        Optional<Courses> courses = coursesRepository.findById(coursesId);
        User user = userRepository.findByEmail(userDetails.getUsername()).get();
        Integer userId = user.getUser_id();

        if(courses.isPresent() == false){
            throw new IllegalArgumentException("Does not exist in this subject");
        }

        if(userId != coursesRepository.findById(coursesId).get().getCreator_id()){
            throw new IllegalArgumentException("You did not create a course, cannot update");
        }

        courses.get().setTitle(updateCourses.getTitle());
        courses.get().setDescription(updateCourses.getDescription());
        courses.get().setCategory(updateCourses.getCategory());
        courses.get().setLevel(updateCourses.getLevel());
        coursesRepository.save(courses.get());

        return courses.get();
    }

    @Transactional
    public void delete(UserDetails userDetails,Integer coursesId) {
        Optional<Courses> courses = coursesRepository.findById(coursesId);
        User user = userRepository.findByEmail(userDetails.getUsername()).get();
        Integer userId = user.getUser_id();

        if(courses.isPresent() == false){
            throw new IllegalArgumentException("Does not exist in this subject");
        }

        if(userId != coursesRepository.findById(coursesId).get().getCreator_id()){
            throw new IllegalArgumentException("You did not create a course, cannot delete");
        }

        coursesRepository.deleteById(coursesId);
    }

    @Transactional
    public Enrollment enrollment_method(UserDetails userDetails,Enrollment enrollment){
        User user = userRepository.findByEmail(userDetails.getUsername()).get();
        Integer userId = user.getUser_id();

        if(enrollmentRepository.findByUserIdAndCourseId(userId,enrollment.getCourseId()) != null){
            throw new IllegalArgumentException("You have registered for this course.");
        }

        enrollment.setUserId(userId);

        enrollmentRepository.save(enrollment);
        return enrollment;
    }

    public List<Courses> filterCourses(String category,String title,Boolean popular) {
        List<Courses> listCourses = coursesRepositoryImp.filterCourses(category, title);

        if(popular == null){
            return listCourses;
        }
        else if(popular == true){ // giảm dần
            listCourses.sort(Comparator.comparing(Courses::getTotalStudents).reversed());
        }
        else// tăng dần
            listCourses.sort(Comparator.comparing(Courses::getTotalStudents));


        return listCourses;
    }
}
