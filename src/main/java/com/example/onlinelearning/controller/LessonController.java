package com.example.onlinelearning.controller;

import com.example.onlinelearning.entity.Lesson;
import com.example.onlinelearning.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/lesson")
public class LessonController {
    @Autowired
    private LessonService lessonService;
    @PreAuthorize("hasRole('TEACHER')")
    @PostMapping("/create")
    public ResponseEntity<Lesson> create(@ModelAttribute Lesson lesson, @RequestParam(value = "file", required = false)MultipartFile file) throws IOException{
           return ResponseEntity.ok(lessonService.create(lesson, file));

    }

    @PostMapping("/view_file")
    public ResponseEntity<?> viewFile(@RequestBody Map<String, Object> Map_lessonId) throws FileNotFoundException {
        return lessonService.viewFile(Map_lessonId);
    }

    @PreAuthorize("hasRole('STUDENT')")
    @PostMapping("/lesson_progress_click_complete") // lấy userid qua token, lesson_id qua json
    public ResponseEntity<?> lessonProgressClickComplete(@AuthenticationPrincipal UserDetails userDetails, @RequestBody Map<String,Object> map_lessonId){
        String userEmail = userDetails.getUsername();
        return lessonService.lessonProgressClickComplete(userEmail, map_lessonId);
    }

    @PreAuthorize("hasRole('STUDENT')")
    @PostMapping("/lesson_progress_select") // lấy userid qua token, courses_id qua json ( từ courses đó để tính phần trăm)
    public ResponseEntity<?> lessonProgressSelect(@AuthenticationPrincipal UserDetails userDetails, @RequestBody Map<String,Object> map_courseId){
        String userEmail = userDetails.getUsername();
        return lessonService.lessonProgressSelect(userEmail, map_courseId);
    }
}
