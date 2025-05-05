package com.example.onlinelearning.controller;

import com.example.onlinelearning.entity.Notification;
import com.example.onlinelearning.entity.NotificationRecipient;
import com.example.onlinelearning.service.NotificationService;
import com.example.onlinelearning.util.NotificationRecipientID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notification")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @PreAuthorize("hasRole('TEACHER')")
    @PostMapping("/send")
    public ResponseEntity<Notification> send(@AuthenticationPrincipal UserDetails userDetails, @RequestBody Notification notification){
        return ResponseEntity.ok(notificationService.send(userDetails,notification));
    }

    @PreAuthorize("hasRole('STUDENT')")
    @PostMapping("/read/{notificationId}")
    public ResponseEntity<NotificationRecipient> read(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Integer notificationId){
        return ResponseEntity.ok(notificationService.read(userDetails,notificationId));
    }
}
