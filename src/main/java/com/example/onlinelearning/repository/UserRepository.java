package com.example.onlinelearning.repository;

import com.example.onlinelearning.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByEmail(String email); // kiểm tra email tồn tại ko?
    Optional<User> findByEmail(String email); // tìm user theo email
}