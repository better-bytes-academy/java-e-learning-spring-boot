package com.example.onlinelearning.config;

import com.example.onlinelearning.entity.User;
import com.example.onlinelearning.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration //file cấu hình

public class ApplicationConfig {
    @Autowired
    private UserRepository userRepository;
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return username -> userRepository.findByEmail(username)
               .orElseThrow( () -> new UsernameNotFoundException("User not found") );
    }// đoạn code này thể hiện đã ghi đè method public UserDetail loadUserByUsername(String username) của UserDetailsService
}
