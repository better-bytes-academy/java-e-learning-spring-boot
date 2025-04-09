package com.example.onlinelearning.service;

import com.example.onlinelearning.entity.User;
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

import java.util.Optional;

@Service

public class AuthenticationService {
    @Autowired
    private  PasswordEncoder passwordEncoder;
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private   JwtService jwtService;

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
}
