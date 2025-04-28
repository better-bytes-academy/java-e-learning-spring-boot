package com.example.onlinelearning.controller;

import com.example.onlinelearning.entity.Assessment;
import com.example.onlinelearning.request.ExamResultRequest;
import com.example.onlinelearning.request.ListAnswerStudentRequest;
import com.example.onlinelearning.service.AssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
    @RequestMapping("/assessment")
public class AssessmentController {
    @Autowired
    private AssessmentService assessmentService;

    @PreAuthorize("hasRole('TEACHER')")
    @PostMapping("/create")
    public ResponseEntity<?> assessmentCreate(@AuthenticationPrincipal UserDetails userDetails, @RequestBody Assessment assessment){
        return assessmentService.assessmentCreate(userDetails, assessment);
    }

    @PreAuthorize("hasRole('STUDENT')")
    @PostMapping("/start")
    public ResponseEntity<?> assessmentStart(@AuthenticationPrincipal UserDetails userDetails, @RequestBody ListAnswerStudentRequest listAnswerStudentRequest){
        return assessmentService.assessmentStart(userDetails, listAnswerStudentRequest) ;
    }

    @PreAuthorize("hasRole('STUDENT')")
    @PostMapping("/result")
    public ResponseEntity<?> assessmentResult(@AuthenticationPrincipal UserDetails userDetails, @RequestBody ExamResultRequest examResultRequest){
        return assessmentService.assessmentResult(userDetails, examResultRequest);
    }
}
