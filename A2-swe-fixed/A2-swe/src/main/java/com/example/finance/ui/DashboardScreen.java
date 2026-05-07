package com.example.finance.ui;

import java.util.Optional;

import com.example.finance.controllers.AppContext;
import com.example.finance.models.Income;
import com.example.finance.models.Transaction;

import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DashboardScreen extends Application {

    @Override
    public void start(Stage stage) {

        Label title =
                new Label("Finance Dashboard");

        title.setStyle(
                "-fx-font-size: 24px;"
                        + "-fx-font-weight: bold;"
        );

        double income =
                AppContext.financeController
                        .getTotalIncome();

        double expense =
                AppContext.financeController
                        .getTotalExpenses();

        double balance =
                AppContext.financeController
                        .getBalance();

        Label balanceLabel =
                new Label("Balance: " + balance);

        Label incomeLabel =
                new Label("Income: " + income);

        Label expenseLabel =
                new Label("Expenses: " + expense);

        balanceLabel.setStyle("-fx-font-size: 16px;");
        incomeLabel.setStyle("-fx-font-size: 16px;");
        expenseLabel.setStyle("-fx-font-size: 16px;");

        // =================================================
        // TABLE
        // =================================================

        TableView<Transaction> table =
                new TableView<>();

        table.setItems(
                FXCollections.observableArrayList(
                        AppContext.financeController
                                .getAllTransactions()
                )
        );

        table.setPrefHeight(300);

        TableColumn<Transaction, Number> idCol =
                new TableColumn<>("ID");

        idCol.setCellValueFactory(data ->
                new SimpleIntegerProperty(
                        data.getValue()
                                .getTransactionId()
                )
        );

        TableColumn<Transaction, String> typeCol =
                new TableColumn<>("Type");

        typeCol.setCellValueFactory(data -> {

            if (data.getValue() instanceof Income) {

                return new SimpleStringProperty(
                        "Income"
                );

            } else {

                return new SimpleStringProperty(
                        "Expense"
                );
            }
        });

        TableColumn<Transaction, Number> amountCol =
                new TableColumn<>("Amount");

        amountCol.setCellValueFactory(data ->
                new SimpleDoubleProperty(
                        data.getValue()
                                .getAmount()
                )
        );

        TableColumn<Transaction, String> notesCol =
                new TableColumn<>("Notes");

        notesCol.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue()
                                .getNotes()
                )
        );

        TableColumn<Transaction, String> dateCol =
                new TableColumn<>("Date");

        dateCol.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue()
                                .getDate()
                                .toString()
                )
        );

        table.getColumns().addAll(
                idCol,
                typeCol,
                amountCol,
                notesCol,
                dateCol
        );

        // =================================================
        // BUTTONS
        // =================================================

        Button addBtn =
                new Button("Add");

        Button editBtn =
                new Button("Edit");

        Button deleteBtn =
                new Button("Delete");

        Button refreshBtn =
                new Button("Refresh");

        Button reportBtn =
                new Button("Reports");

        Button budgetBtn =
                new Button("Budget");

        Button darkModeBtn =
                new Button("Dark Mode");

        Button logoutBtn =
                new Button("Logout");

        HBox buttons =
                new HBox(10);

        buttons.getChildren().addAll(
                addBtn,
                editBtn,
                deleteBtn,
                refreshBtn,
                reportBtn,
                budgetBtn,
                darkModeBtn,
                logoutBtn
        );

        // =================================================
        // ADD
        // =================================================

        addBtn.setOnAction(e -> {

            try {

                new AddTransactionScreen()
                        .start(new Stage());

            } catch (Exception ex) {

                ex.printStackTrace();
            }
        });

        // =================================================
        // DELETE
        // =================================================

        deleteBtn.setOnAction(e -> {

            Transaction selected =
                    table.getSelectionModel()
                            .getSelectedItem();

            if (selected == null) {

                showError("Select transaction first");
                return;
            }

            Alert confirm =
                    new Alert(
                            Alert.AlertType.CONFIRMATION
                    );

            confirm.setHeaderText(
                    "Delete Transaction?"
            );

            Optional<ButtonType> result =
                    confirm.showAndWait();

            if (result.isPresent()
                    && result.get()
                    == ButtonType.OK) {

                AppContext.financeController
                        .deleteTransaction(
                                selected.getTransactionId()
                        );

                refresh(stage);
            }
        });

        // =================================================
        // EDIT
        // =================================================

        editBtn.setOnAction(e -> {

            Transaction selected =
                    table.getSelectionModel()
                            .getSelectedItem();

            if (selected == null) {

                showError("Select transaction first");
                return;
            }

            TextInputDialog amountDialog =
                    new TextInputDialog(
                            String.valueOf(
                                    selected.getAmount()
                            )
                    );

            amountDialog.setHeaderText(
                    "New Amount"
            );

            amountDialog.showAndWait()
                    .ifPresent(amountText -> {

                        try {

                            double amount =
                                    Double.parseDouble(
                                            amountText
                                    );

                            TextInputDialog notesDialog =
                                    new TextInputDialog(
                                            selected.getNotes()
                                    );

                            notesDialog.setHeaderText(
                                    "New Notes"
                            );

                            notesDialog.showAndWait()
                                    .ifPresent(notes -> {

                                        AppContext.financeController
                                                .updateTransaction(
                                                        selected.getTransactionId(),
                                                        amount,
                                                        notes
                                                );

                                        refresh(stage);
                                    });

                        } catch (Exception ex) {

                            showError(
                                    "Invalid amount"
                            );
                        }
                    });
        });

        // =================================================
        // REFRESH
        // =================================================

        refreshBtn.setOnAction(e ->
                refresh(stage)
        );

        // =================================================
        // REPORT
        // =================================================

        reportBtn.setOnAction(e -> {

            try {

                new ReportScreen()
                        .start(new Stage());

            } catch (Exception ex) {

                ex.printStackTrace();
            }
        });

        // =================================================
        // BUDGET
        // =================================================

        budgetBtn.setOnAction(e -> {

            try {

                new BudgetScreen()
                        .start(new Stage());

            } catch (Exception ex) {

                ex.printStackTrace();
            }
        });

        // =================================================
        // ROOT
        // =================================================

        VBox root =
                new VBox(15);

        root.setPadding(
                new Insets(20)
        );

        root.getChildren().addAll(
                title,
                balanceLabel,
                incomeLabel,
                expenseLabel,
                table,
                buttons
        );

        final Scene scene =
                new Scene(root, 900, 550);

        // =================================================
        // DARK MODE
        // =================================================

        darkModeBtn.setOnAction(e -> {

            if (scene.getStylesheets().isEmpty()) {

                scene.getStylesheets().add(
                        getClass()
                                .getResource(
                                        "/static/dark-theme.css"
                                )
                                .toExternalForm()
                );

            } else {

                scene.getStylesheets().clear();
            }
        });

        // =================================================
        // LOGOUT
        // =================================================

        logoutBtn.setOnAction(e -> {

            try {

                AppContext.financeController =
                        null;

                new LoginScreen()
                        .start(stage);

            } catch (Exception ex) {

                ex.printStackTrace();
            }
        });

        stage.setTitle(
                "Dashboard"
        );

        stage.setScene(scene);

        stage.show();
    }

    // =================================================
    // REFRESH SCREEN
    // =================================================

    private void refresh(Stage stage) {

        try {

            new DashboardScreen()
                    .start(stage);

        } catch (Exception ex) {

            ex.printStackTrace();
        }
    }

    // =================================================
    // ERROR ALERT
    // =================================================

    private void showError(String message) {

        Alert alert =
                new Alert(
                        Alert.AlertType.ERROR
                );

        alert.setContentText(message);

        alert.show();
    }
}