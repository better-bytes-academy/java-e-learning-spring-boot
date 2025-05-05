package com.example.onlinelearning.controller;

import com.example.onlinelearning.entity.NotificationRecipient;
import com.example.onlinelearning.response.CourseStatisticResponse;
import com.example.onlinelearning.response.StudentStatisticsResponse;
import com.example.onlinelearning.service.ReportAndStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistic")
public class ReportAndStatisticsController {
    @Autowired
    private ReportAndStatisticsService reportAndStatisticsService;

    @PreAuthorize("hasRole('TEACHER')")
    @PostMapping("/student_progress/{courseId}")
    public ResponseEntity<StudentStatisticsResponse> studentProgress(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Integer courseId){
        return ResponseEntity.ok(reportAndStatisticsService.studentProgress(userDetails,courseId));
    }

    @PreAuthorize("hasRole('TEACHER')")
    @PostMapping("/course_statistic/{courseId}")
    public ResponseEntity<CourseStatisticResponse> courseStatistic(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Integer courseId){
        return ResponseEntity.ok(reportAndStatisticsService.courseStatistic(userDetails,courseId));
    }
}
