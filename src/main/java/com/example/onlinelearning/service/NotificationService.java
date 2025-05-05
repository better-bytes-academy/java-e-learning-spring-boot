package com.example.onlinelearning.service;

import com.example.onlinelearning.entity.Courses;
import com.example.onlinelearning.entity.Notification;
import com.example.onlinelearning.entity.NotificationRecipient;
import com.example.onlinelearning.entity.User;
import com.example.onlinelearning.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private NotificationRecipientRepository notificationRecipientRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CoursesRepository coursesRepository;
    @Autowired
    private EnrollmentRepository enrollmentRepository;


    @Transactional
    public Notification send(UserDetails userDetails, Notification notification) {
        User user = userRepository.findByEmail(userDetails.getUsername()).get();
        Integer userId = user.getUser_id();

        if(userId != coursesRepository.findById(notification.getCourseId()).get().getCreator_id()){
            throw new IllegalArgumentException("You are not the course creator");
        }

        notificationRepository.save(notification);

        List<Integer> listUserId = enrollmentRepository.selectListUserIdByCourseId(notification.getCourseId());

        for (int i = 0 ; i < listUserId.size() ; i++){
            NotificationRecipient temp = new NotificationRecipient();
            temp.setNotificationId(notification.getId());
            temp.setUserId(listUserId.get(i));

            notificationRecipientRepository.save(temp);
        }

        return notification;
    }

    @Transactional
    public NotificationRecipient read(UserDetails userDetails, Integer notificationId) {
        User user = userRepository.findByEmail(userDetails.getUsername()).get();
        Integer userId = user.getUser_id();

        if(notificationRecipientRepository.findByNotificationIdAndUserId(notificationId,userId) == null){
            throw new IllegalArgumentException("Cannot view notification");
        }

        NotificationRecipient notificationRecipient = new NotificationRecipient();
        notificationRecipient.setIsRead(true);
        notificationRecipient.setNotificationId(notificationId);
        notificationRecipient.setUserId(userId);

        notificationRecipientRepository.save(notificationRecipient);

        return notificationRecipient;
    }
}
