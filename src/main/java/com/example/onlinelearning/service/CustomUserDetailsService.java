package com.example.onlinelearning.service;

import com.example.onlinelearning.entity.User;
import com.example.onlinelearning.enums.RoleName;
import com.example.onlinelearning.repository.UserRepository;
import com.example.onlinelearning.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Tìm người dùng theo email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Lấy danh sách các vai trò của người dùng từ bảng UserRole
        List<RoleName> userRoles = userRoleRepository.findRolesByUserId(user.getUser_id());

        // Gán các vai trò vào đối tượng user
        List<GrantedAuthority> authorities = userRoles.stream()
                .map(role -> new SimpleGrantedAuthority(role.name())) // Chuyển đổi RoleName thành GrantedAuthority
                .collect(Collectors.toList());

        // Trả về đối tượng UserDetails cho Spring Security
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPasswordHash(), // Mật khẩu đã mã hóa
                authorities // Các quyền hạn (roles) của người dùng
        );
    }
}
