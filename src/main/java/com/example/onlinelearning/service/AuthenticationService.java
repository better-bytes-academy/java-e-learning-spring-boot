package com.example.onlinelearning.service;

import com.example.onlinelearning.entity.ResetPassword;
import com.example.onlinelearning.entity.User;
import com.example.onlinelearning.repository.ResetPasswordRepository;
import com.example.onlinelearning.repository.UserRepository;
import com.example.onlinelearning.request.AuthenticationRequest;
import com.example.onlinelearning.request.RegisterRequest;
import com.example.onlinelearning.response.AuthenticationResponse;
import com.example.onlinelearning.util.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service

public class AuthenticationService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private ResetPasswordRepository resetPasswordRepository;
    public AuthenticationResponse register(RegisterRequest registerRequest){
        if(userRepository.existsByEmail(registerRequest.getEmail()) == true){
            throw new IllegalArgumentException("Email already exists");
        }

        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setName(registerRequest.getName());
        user.setPasswordHash(passwordEncoder.encode(registerRequest.getPassword()));
        user.setRole(registerRequest.getRole());

        userRepository.save(user); // lưu thnahf công
        String token = jwtService.generateToken(user);
        return new AuthenticationResponse(token);
    }

    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
        Optional<User> user = userRepository.findByEmail(authenticationRequest.getEmail());

        if (user.isPresent() == true){//tồn tại email
            if(passwordEncoder.matches(authenticationRequest.getPassword(), user.get().getPasswordHash())){ // password giống
                String token = jwtService.generateToken(user.get());
                return new AuthenticationResponse(token);
            }
            else{
                throw new IllegalArgumentException("Login failed");
            }
        }
        else{// không tồn tại email
            throw new IllegalArgumentException("Login failed");
        }
    }

    public Map<String,Object> forgotPassword(Map<String,Object> mapEmail){
        String email = mapEmail.get("email").toString();
        String token = UUID.randomUUID().toString().substring(0,6);

        Optional<User> user = userRepository.findByEmail(email);

        if(user.isPresent() == false){ // không tồn tại
            throw new IllegalArgumentException("Email does not exist in the system");
        }

        emailService.sendResetEmail(email,token);

        ResetPassword resetPassword = new ResetPassword();
        resetPassword.setEmail(email);
        resetPassword.setToken(token);

        resetPasswordRepository.save(resetPassword);

        Map<String,Object> message = new LinkedHashMap<>();
        message.put("message", "Access email to reset password");

        return message;
    }

    public Map<String, Object> resetPassword(String email, String token, Map<String, Object> mapNewPassword) {
        if(mapNewPassword.get("newPassWord") == null || mapNewPassword.get("confirmPassWord") == null){
            throw new IllegalArgumentException("Password must be at least 4 characters and cannot be blank");
        }

        String newPassWord = mapNewPassword.get("newPassWord").toString();
        String confirmPassWord = mapNewPassword.get("confirmPassWord").toString();

        if(email == null || token == null){
            throw new IllegalArgumentException("You cannot access this page");
        }

        if(newPassWord == null || newPassWord.length() < 4){
            throw new IllegalArgumentException("Password must be at least 4 characters and cannot be blank");
        }

        if(newPassWord.equals(confirmPassWord) == false){
            throw new IllegalArgumentException("New password and confirm password are not the same");
        }

        User user = userRepository.findByEmail(email).get();
        if(passwordEncoder.matches(newPassWord, user.getPasswordHash()) == true){
            throw new IllegalArgumentException("This is the old password");
        }

        // cả 2 đều có giá trị
        ResetPassword resetPassword = resetPasswordRepository.findByEmailAndToken(email,token);
        Timestamp now = new Timestamp(System.currentTimeMillis());

        if(resetPassword.getExpiryDate().after(now) == false){
            throw new IllegalArgumentException("Token expired, please click resend token");
        }

        user.setPasswordHash(passwordEncoder.encode(newPassWord));
        userRepository.save(user);

        resetPasswordRepository.updateNullForTokenAndTime(email);

        Map<String,Object> message = new LinkedHashMap<>();
        message.put("message","Password change successful");

        return message;
    }
}
