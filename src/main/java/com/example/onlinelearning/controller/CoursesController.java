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

import java.util.List;
import java.util.Map;

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
    public ResponseEntity<Courses> update(@AuthenticationPrincipal UserDetails userDetails,@Valid @RequestBody UpdateCourses updateCourses, @PathVariable Integer coursesId){
        return ResponseEntity.ok(courseService.update(userDetails,updateCourses,coursesId));
    }

    @PreAuthorize("hasRole('TEACHER')")
    @PostMapping("/delete")
    public ResponseEntity<String> delete(@AuthenticationPrincipal UserDetails userDetails,@RequestParam Integer coursesId){
        courseService.delete(userDetails,coursesId);
        return ResponseEntity.ok("Delete course successfully");
    }

    @PreAuthorize("hasRole('STUDENT')")
    @PostMapping("/enrollment")
    public ResponseEntity<?> enrollment_method(@AuthenticationPrincipal UserDetails userDetails,@RequestBody Enrollment enrollment){
        return ResponseEntity.ok(courseService.enrollment_method(userDetails,enrollment));
    }

    @PostMapping("/filter-courses")
    public ResponseEntity<List<Courses>> filterCourses(@RequestParam(required = false) String category, @RequestParam(required = false) String title, @RequestParam(required = false) Boolean popular){
        return ResponseEntity.ok(courseService.filterCourses(category,title,popular));
    }
}
