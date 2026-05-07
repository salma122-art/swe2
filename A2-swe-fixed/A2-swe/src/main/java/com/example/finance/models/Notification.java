package com.example.finance.models;

import java.util.Date;

/**
 * Handles system alerts and messages sent to the user.
 */
public class Notification {

    private int notificationId;
    private String message;
    private Date timestamp;
    private boolean isRead;

    public Notification() {
    }

    public Notification(int notificationId, String message) {
        this.notificationId = notificationId;
        this.message = message;
        this.timestamp = new Date();
        this.isRead = false;
    }

    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    /**
     * Marks the notification as read by the user.
     */
    public void markAsRead() {
        this.isRead = true;
    }
}