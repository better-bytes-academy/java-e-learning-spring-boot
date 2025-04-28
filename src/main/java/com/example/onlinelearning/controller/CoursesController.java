package com.example.onlinelearning.controller;

import com.example.onlinelearning.entity.Courses;
import com.example.onlinelearning.entity.Enrollment;
import com.example.onlinelearning.request.UpdateCourses;
import com.example.onlinelearning.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
public class CoursesController {
    @Autowired
    private CourseService courseService;
    @PreAuthorize("hasRole('TEACHER')")
    @PostMapping("/create")
    public ResponseEntity<Courses> create(@Valid @RequestBody Courses courses, @AuthenticationPrincipal UserDetails userDetails){
        return ResponseEntity.ok(courseService.create(userDetails, courses));
    }

    @PreAuthorize("hasRole('TEACHER')")
    @PostMapping("/update/{coursesId}")
    public ResponseEntity<Courses> update(@Valid @RequestBody UpdateCourses updateCourses, @PathVariable Integer coursesId){
        return ResponseEntity.ok(courseService.update(updateCourses,coursesId));
    }

    @PreAuthorize("hasRole('TEACHER')")
    @PostMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam Integer coursesId){
        courseService.delete(coursesId);
        return ResponseEntity.ok("Delete course successfully");
    }

    @PreAuthorize("hasRole('STUDENT')")
    @PostMapping("/enrollment")
    public ResponseEntity<?> enrollment_method(@RequestBody Enrollment enrollment){
        return ResponseEntity.ok(courseService.enrollment_method(enrollment));
    }
}
