package com.example.onlinelearning.controller;

import com.example.onlinelearning.request.AuthenticationRequest;
import com.example.onlinelearning.request.RegisterRequest;
import com.example.onlinelearning.response.AuthenticationResponse;
import com.example.onlinelearning.service.AuthenticationService;
import com.example.onlinelearning.util.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController

public class AuthenticationController {
    @Autowired
    private JwtService jwtService;

    @Autowired
    private  AuthenticationService authenticationService;
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@Valid  @RequestBody RegisterRequest registerRequest){
       return ResponseEntity.ok(authenticationService.register(registerRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@Valid @RequestBody AuthenticationRequest authenticationRequest){
        return ResponseEntity.ok(authenticationService.login(authenticationRequest));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String authHeader){
        String token = authHeader.substring(7);
        jwtService.addBlackList(token);
        return ResponseEntity.ok("Logout Successfully");
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<Map<String,Object>> forgotPassword(@RequestBody Map<String,Object> mapEmail){
        return ResponseEntity.ok(authenticationService.forgotPassword(mapEmail));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<Map<String,Object>> resetPassword(@RequestParam(required = false) String email, @RequestParam(required = false) String token, @RequestBody Map<String,Object> mapNewPassword){
        return ResponseEntity.ok(authenticationService.resetPassword(email,token,mapNewPassword));
    }
}
