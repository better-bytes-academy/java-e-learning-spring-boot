package com.example.onlinelearning.repository;

import com.example.onlinelearning.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    public Optional<User> findByEmail(String email); // tìm user theo email
    public boolean existsByEmail(String email);
}