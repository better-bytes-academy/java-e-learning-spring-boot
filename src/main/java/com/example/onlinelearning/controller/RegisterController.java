package com.example.onlinelearning.controller;

import com.example.onlinelearning.entity.User;
import com.example.onlinelearning.enums.RoleName;
import com.example.onlinelearning.response.JwtResponse;
import com.example.onlinelearning.service.UserService;
import com.example.onlinelearning.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegisterController {


    @Autowired
    private  UserService userService;

    @Autowired
    private JwtUtil jwtUtil;



    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user, @RequestParam RoleName roleName) {
        if(roleName == RoleName.admin ){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bạn không có quyền đăng kí vai trò ADMIN");
        }
        User newUser = userService.registerUser(user, roleName);
        String token = jwtUtil.generateToken(newUser.getEmail());
        return ResponseEntity.ok( new JwtResponse(token));
      //  return ResponseEntity.ok("Đăng ký thành công! User ID: " + newUser.getUser_id());
    }
    //http://localhost:8080/api/auth/register?roleName=student

    //    {
    //        "email": "testuser@example.com",
    //            "passwordHash": "mypassword",
    //            "fullName": "Test User"
    //    }

}