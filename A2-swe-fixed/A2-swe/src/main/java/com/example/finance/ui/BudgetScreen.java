package com.example.finance.ui;

import java.util.List;

import com.example.finance.controllers.AppContext;
import com.example.finance.models.Budget;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BudgetScreen extends Application {

    @Override
    public void start(Stage stage) {

        Label title = new Label("Budget Screen");

        VBox budgetListBox = new VBox(10);

        Label alertLabel = new Label();

        Button refreshButton = new Button("Refresh");

        loadBudgets(budgetListBox, alertLabel);

        refreshButton.setOnAction(e -> {

            budgetListBox.getChildren().clear();

            loadBudgets(budgetListBox, alertLabel);
        });

        VBox root = new VBox(15);

        root.setStyle("-fx-padding: 20;");

        root.getChildren().addAll(
                title,
                budgetListBox,
                refreshButton,
                alertLabel
        );

        Scene scene = new Scene(root, 450, 350);

        stage.setTitle("Budget Screen");
        stage.setScene(scene);
        stage.show();
    }

    private void loadBudgets(VBox container,
                             Label alertLabel) {

        List<Budget> budgets =
                AppContext.financeController.getBudgets();

        if (budgets == null || budgets.isEmpty()) {

            container.getChildren().add(
                    new Label("No budgets found")
            );

            return;
        }

        boolean exceeded = false;

        for (Budget b : budgets) {

            String status;

            if (b.checkLimit()) {

                status = "OVER BUDGET";
                exceeded = true;

            } else {

                status = "OK";
            }

            Label budgetLabel = new Label(
                    "Budget ID: " + b.getBudgetId()
                            + " | Limit: " + b.getLimitAmount()
                            + " | Spending: " + b.getCurrentSpending()
                            + " | Remaining: " + b.getRemainingAmount()
                            + " | Status: " + status
            );

            container.getChildren().add(budgetLabel);
        }

        if (exceeded) {

            alertLabel.setText(
                    "Warning: Some budgets exceeded the limit"
            );

        } else {

            alertLabel.setText(
                    "All budgets are within limits"
            );
        }
    }
    
}