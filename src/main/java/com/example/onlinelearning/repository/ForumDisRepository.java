package com.example.onlinelearning.repository;

import com.example.onlinelearning.entity.ForumDis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForumDisRepository extends JpaRepository<ForumDis, Integer> {
}
