package com.example.onlinelearning;

import com.example.onlinelearning.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface repo extends JpaRepository<User, Integer> {
    
}
