package com.example.onlinelearning.util;

import java.io.Serializable;
import java.util.Objects;

public class NotificationRecipientID implements Serializable {
    private Integer notificationId;
    private Integer userId;

    public NotificationRecipientID() {
    }

    public NotificationRecipientID(Integer notificationId, Integer userId) {
        this.notificationId = notificationId;
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificationRecipientID that = (NotificationRecipientID) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(notificationId, that.notificationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, notificationId);
    }

    public Integer getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Integer notificationId) {
        this.notificationId = notificationId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
