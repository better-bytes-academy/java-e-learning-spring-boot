package com.example.onlinelearning.repository;

import com.example.onlinelearning.entity.NotificationRecipient;
import com.example.onlinelearning.util.NotificationRecipientID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRecipientRepository extends JpaRepository<NotificationRecipient, NotificationRecipientID> {

    NotificationRecipient findByNotificationIdAndUserId(Integer notificationId, Integer userId);


}
