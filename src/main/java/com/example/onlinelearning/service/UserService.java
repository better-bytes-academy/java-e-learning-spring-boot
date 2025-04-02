package com.example.onlinelearning.service;

import com.example.onlinelearning.entity.User;
import com.example.onlinelearning.entity.Role;
import com.example.onlinelearning.entity.UserRole;
import com.example.onlinelearning.enums.RoleName;
import com.example.onlinelearning.enums.UserStatus;
import com.example.onlinelearning.repository.UserRepository;
import com.example.onlinelearning.repository.RoleRepository;
import com.example.onlinelearning.repository.UserRoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, UserRoleRepository userRoleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User registerUser(User user, RoleName roleName) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email đã được sử dụng!");
        }
        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        user.setStatus(UserStatus.active);
        User savedUser = userRepository.save(user);

        Role role = roleRepository.findByRoleName(roleName)
                .orElseThrow(() -> new RuntimeException("Vai trò không tồn tại!"));

        UserRole userRole = new UserRole(savedUser.getUser_id(), role.getRoleId());
        userRoleRepository.save(userRole);

        return savedUser;
    }
}