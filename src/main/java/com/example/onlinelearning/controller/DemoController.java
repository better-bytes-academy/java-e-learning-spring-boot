package com.example.onlinelearning.controller;

import com.example.onlinelearning.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {
    @GetMapping("/demo-controller-permitAll")
    public ResponseEntity<String> demo1(){
        return  ResponseEntity.ok("không cần đăng nhập");
        //return ResponseEntity.ok(userDetails.getUsername() + "\n" + userDetails.getAuthorities());
    }

    @GetMapping("/homePage")
    public ResponseEntity<String> homePage(){
        return  ResponseEntity.ok("đã được xác thực, ko cần quyền");
    }

    @GetMapping("helloStudent")
    public ResponseEntity<String> helloStudent(@AuthenticationPrincipal UserDetails userDetails){
        return  ResponseEntity.ok("đã xác thực và phân quyền STUDENT\n" + userDetails.getUsername() + "\n" +userDetails.getAuthorities().toString() );
    }

    @GetMapping("helloTeacher")
    public ResponseEntity<String> hellTeacher(@AuthenticationPrincipal UserDetails userDetails){
        return  ResponseEntity.ok("đã xác thực và phân quyền TEACHER\n" + userDetails.getUsername() + "\n" +userDetails.getAuthorities().toString() );
    }

    @GetMapping("helloAdmin")
    public ResponseEntity<String> helloAdmin(@AuthenticationPrincipal UserDetails userDetails){
        return  ResponseEntity.ok("đã xác thực và phân quyền ADMIN\n" + userDetails.getUsername() + "\n" +userDetails.getAuthorities().toString() );
    }
}
