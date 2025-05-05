package com.example.onlinelearning.entity;

import com.example.onlinelearning.util.NotificationRecipientID;
import jakarta.persistence.*;
import lombok.Getter;

import java.io.Serializable;

@Entity
@Table(name = "notification_recipient")
@IdClass(NotificationRecipientID.class)
public class NotificationRecipient implements Serializable {

    @Id
    @Column(name = "notification_id", nullable = false)
    private Integer notificationId;


    @Id
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "is_read", nullable = false)
    private Boolean isRead = false;

    public NotificationRecipient() {
    }

    public NotificationRecipient(Integer notificationId, Integer userId, Boolean isRead) {
        this.notificationId = notificationId;
        this.userId = userId;
        this.isRead = isRead;
    }

    public Integer getNotificationId() {
        return notificationId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setNotificationId(Integer notificationId) {
        this.notificationId = notificationId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean read) {
        isRead = read;
    }
}
