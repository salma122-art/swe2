package com.example.finance.ui;

import com.example.finance.controllers.AppContext;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Date;

public class AddTransactionScreen extends Application {

    @Override
    public void start(Stage stage) {

        Label title = new Label("Add Transaction");

        TextField amountField = new TextField();
        amountField.setPromptText("Amount");

        ChoiceBox<String> typeChoice = new ChoiceBox<>();
        typeChoice.getItems().addAll("Income", "Expense");

        TextField categoryField = new TextField();
        categoryField.setPromptText("Category ID");

        TextField notesField = new TextField();
        notesField.setPromptText("Notes");

        Label messageLabel = new Label();

        Button saveButton = new Button("Save Transaction");

        VBox root = new VBox(10);
        root.setStyle("-fx-padding: 20;");
        root.getChildren().addAll(
                title, amountField, typeChoice, categoryField, notesField,
                saveButton, messageLabel
        );

        saveButton.setOnAction(e -> {
            try {
                String amountText = amountField.getText();
                String type = typeChoice.getValue();
                String categoryText = categoryField.getText();
                String notes = notesField.getText();

                if (amountText.isEmpty() || type == null || categoryText.isEmpty()) {
                    messageLabel.setText("Please fill all fields");
                    return;
                }
try {
    double amount = Double.parseDouble(amountText.trim());
    int categoryId = Integer.parseInt(categoryText.trim());

    boolean success = AppContext.financeController.addTransaction(
            amount, type, categoryId, new Date(), notes
    );

    if (success) {
        messageLabel.setText("Transaction added successfully");
    } else {
        messageLabel.setText("Failed to add transaction");
    }

} catch (NumberFormatException ex) {
    messageLabel.setText("⚠ Enter valid numbers only (Amount: 100, Category: 1)");
}

                boolean success = AppContext.financeController.addTransaction(
                        amount, type, categoryId, new Date(), notes
                );

                if (success) {
                    messageLabel.setText("Transaction added successfully");
                    amountField.clear();
                    categoryField.clear();
                    notesField.clear();
                    typeChoice.setValue(null);
                } else {
                    messageLabel.setText("Failed to add transaction");
                }

            } catch (NumberFormatException ex) {
                messageLabel.setText("Invalid number format");
            }
        });

        Scene scene = new Scene(root, 400, 350);
        stage.setTitle("Add Transaction");
        stage.setScene(scene);
        stage.show();
    }
}
