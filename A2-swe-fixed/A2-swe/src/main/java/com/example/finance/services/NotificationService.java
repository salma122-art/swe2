package com.example.finance.services;

import com.example.finance.models.Budget;
import com.example.finance.models.Notification;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class NotificationService {

    /**
     * Popup alert when budget exceeded.
     */
    public void sendBudgetAlert() {

        try {

            Alert alert =
                    new Alert(AlertType.WARNING);

            alert.setTitle("Budget Alert");

            alert.setHeaderText(
                    "Budget Limit Exceeded"
            );

            alert.setContentText(
                    "Warning: Budget limit exceeded!"
            );

            alert.showAndWait();

        } catch (Throwable e) {

            System.out.println(
                    "Budget Warning: Budget limit exceeded!"
            );
        }
    }

    /**
     * Popup alert when budget is almost exceeded.
     */
    public void sendBudgetWarningAlert() {

        try {

            Alert alert =
                    new Alert(AlertType.WARNING);

            alert.setTitle("Budget Warning");

            alert.setHeaderText(
                    "Budget Almost Exceeded"
            );

            alert.setContentText(
                    "Warning: Budget is almost exceeded!"
            );

            alert.showAndWait();

        } catch (Throwable e) {

            System.out.println(
                    "Budget Warning: Budget is almost exceeded!"
            );
        }
    }

    /**
     * Success popup.
     */
    public void showSuccessNotification(
            String message) {

        try {

            Alert alert =
                    new Alert(AlertType.INFORMATION);

            alert.setTitle("Success");

            alert.setHeaderText(null);

            alert.setContentText(message);

            alert.showAndWait();

        } catch (Throwable e) {

            System.out.println(
                    "SUCCESS: " + message
            );
        }
    }

    /**
     * Error popup.
     */
    public void showErrorNotification(
            String message) {

        try {

            Alert alert =
                    new Alert(AlertType.ERROR);

            alert.setTitle("Error");

            alert.setHeaderText(null);

            alert.setContentText(message);

            alert.showAndWait();

        } catch (Throwable e) {

            System.out.println(
                    "ERROR: " + message
            );
        }
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
    public Notification createBudgetWarningNotification(
            int id,
            Budget budget) {

        double remaining =
                budget.getRemainingAmount();

        if (remaining <=
                (budget.getLimitAmount() * 0.2)) {

            return new Notification(
                    id,
                    "Warning: Budget is almost exceeded for month "
                            + budget.getMonth()
            );
        }

        return null;
    }
}