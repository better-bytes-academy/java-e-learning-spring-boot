package com.example.onlinelearning.controller;

import com.example.onlinelearning.entity.Lesson;
import com.example.onlinelearning.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasRole('TEACHER')")
    @PostMapping("/view_file")
    public ResponseEntity<?> viewFile(@RequestBody Map<String, Object> Map_lessonId) throws FileNotFoundException {
        return lessonService.viewFile(Map_lessonId);
    }
}
