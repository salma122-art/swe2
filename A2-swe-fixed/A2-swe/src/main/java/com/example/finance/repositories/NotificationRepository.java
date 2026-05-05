package com.example.finance.repositories;

import com.example.finance.models.Notification;

/**
 * Repository for Notification data operations.
 */
public class NotificationRepository extends BaseRepository<Notification> {

    public NotificationRepository() {
        super("notifications.json");
    }

    @Override
    protected Class<Notification> getType() {
        return Notification.class;
    }
}
