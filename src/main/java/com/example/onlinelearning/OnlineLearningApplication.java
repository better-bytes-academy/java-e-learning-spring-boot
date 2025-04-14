package com.example.onlinelearning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"com.example.onlinelearning.entity"})
@EnableJpaRepositories(basePackages = {"com.example.onlinelearning.repository"})
public class OnlineLearningApplication {

    public static void main(String[] args) {

        SpringApplication.run(OnlineLearningApplication.class, args);

        System.out.println("hello world");
    }

}
