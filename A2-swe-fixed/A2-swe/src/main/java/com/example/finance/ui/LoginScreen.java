package com.example.finance.ui;

import com.example.finance.controllers.AppContext;
import com.example.finance.models.User;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginScreen extends Application {

    @Override
    public void start(Stage stage) {

        Label title = new Label("Login");

        TextField emailField = new TextField();
        emailField.setPromptText("Email");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        Label messageLabel = new Label();

        Button loginButton = new Button("Login");

        VBox root = new VBox(10);
        root.setStyle("-fx-padding: 20;");
        root.getChildren().addAll(title, emailField, passwordField, loginButton, messageLabel);

        loginButton.setOnAction(e -> {
            String email = emailField.getText();
            String password = passwordField.getText();

            boolean success = AppContext.authController.authenticate(email, password);

            if (success) {
                User user = AppContext.authController.getUser(email);
                AppContext.initFinanceController(user);

                messageLabel.setText("Login Success");
                try {
                    new DashboardScreen().start(stage);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                messageLabel.setText("Invalid email or password");
            }
        });

        Scene scene = new Scene(root, 300, 250);
        stage.setTitle("Login Screen");
        stage.setScene(scene);
        stage.show();
    }
}
