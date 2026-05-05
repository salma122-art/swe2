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

    public Notification(int notificationId, String message) {
        this.notificationId = notificationId;
        this.message = message;
        this.timestamp = new Date();
        this.isRead = false;
    }

    public int getNotificationId() { return notificationId; }
    public String getMessage() { return message; }
    public Date getTimestamp() { return timestamp; }
    public boolean isRead() { return isRead; }

    /**
     * Marks the notification as read by the user.
     */
    public void markAsRead() {
        this.isRead = true;
    }
}
