package com.example.finance.services;

import com.example.finance.models.Budget;
import com.example.finance.models.Notification;

public class NotificationService {

    public void sendBudgetAlert() {

        System.out.println("Warning: Budget limit exceeded!");
    }

    /**
     * Creates notification object for exceeded budget.
     */
    public Notification createBudgetExceededNotification(int id) {

        return new Notification(
                id,
                "Warning: Budget limit exceeded!"
        );
    }

    /**
     * Creates warning notification when budget is nearly exceeded.
     */
    public Notification createBudgetWarningNotification(int id,
                                                        Budget budget) {

        double remaining = budget.getRemainingAmount();

        if (remaining <= (budget.getLimitAmount() * 0.2)) {

            return new Notification(
                    id,
                    "Warning: Budget is almost exceeded for month "
                            + budget.getMonth()
            );
        }

        return null;
    }
}