package com.example.onlinelearning.filter;

import com.example.onlinelearning.repository.UserRepository;
import com.example.onlinelearning.util.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private  JwtService jwtService;

    @Autowired
    private  UserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,@NonNull HttpServletResponse response,@NonNull FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;

        if( authHeader == null || !authHeader.startsWith("Bearer ") ){ // ko có token
            filterChain.doFilter(request,response);
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401 Unauthorized
//            response.setContentType("application/json");
//            response.getWriter().write("{\"message\": \"Missing or invalid Authorization header.\"}");
            return;
        }

        jwt = authHeader.substring(7);
        final String userEmail = jwtService.extractUsername(jwt); // lấy email trong token vừa gwuri đến

        if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null){ // chưa được xác thực
            UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail); // lấy thông tin từ db

            if(!userDetails.isAccountNonLocked()){
                response.setStatus(HttpServletResponse.SC_FORBIDDEN); // 403 đã xác thực nhưng không
                response.setContentType("application/json");
                response.getWriter().write("{\"message\": \"Your account has been banned. Please contact support.\"}");
                return;
            }

            if(jwtService.isTokenValid(jwt,userDetails)){ // email chính xác và chưa hết hạn
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request,response);
    }
}
