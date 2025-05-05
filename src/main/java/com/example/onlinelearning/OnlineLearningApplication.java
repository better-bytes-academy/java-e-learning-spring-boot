package com.example.onlinelearning;

import com.example.onlinelearning.util.EnrollmentId;
import com.example.onlinelearning.util.NotificationRecipientID;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@EntityScan(basePackages = {"com.example.onlinelearning.entity"})
@EnableJpaRepositories(basePackages = {"com.example.onlinelearning.repository"})
public class OnlineLearningApplication {

    public static void main(String[] args) {

        SpringApplication.run(OnlineLearningApplication.class, args);

        System.out.println("hello world");
        Set<NotificationRecipientID> set = new HashSet<>();
        set.add(new NotificationRecipientID(1,2));
        set.add(new NotificationRecipientID(2,1));
        set.add(new NotificationRecipientID(1,1));
        set.add(new NotificationRecipientID(1,1));

        for (NotificationRecipientID value: set){
            System.out.println(value.getNotificationId() + "\t" + value.getUserId());
        }
    }

}
