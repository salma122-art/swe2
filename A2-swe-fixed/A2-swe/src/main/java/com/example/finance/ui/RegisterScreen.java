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

public class RegisterScreen extends Application {

    @Override
    public void start(Stage stage) {

        Label title = new Label("Create Account");

        TextField nameField = new TextField();
        nameField.setPromptText("Name");

        TextField emailField = new TextField();
        emailField.setPromptText("Email");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        Label messageLabel = new Label();

        Button registerButton = new Button("Register");

        Button backButton = new Button("Back To Login");

        VBox root = new VBox(10);

        root.setStyle("-fx-padding: 20;");

        root.getChildren().addAll(
                title,
                nameField,
                emailField,
                passwordField,
                registerButton,
                backButton,
                messageLabel
        );

        registerButton.setOnAction(e -> {

            try {

                String name = nameField.getText().trim();
                String email = emailField.getText().trim();
                String password = passwordField.getText().trim();

                if (name.isEmpty()
                        || email.isEmpty()
                        || password.isEmpty()) {

                    messageLabel.setText(
                            "Please fill all fields"
                    );

                    return;
                }

                User user = new User(
                        AppContext.authController
                                .getAllUsers().size() + 1,
                        name,
                        email,
                        password,
                        0.0
                );

                boolean success =
                        AppContext.authController
                                .register(user);

                if (success) {

                    messageLabel.setText(
                            "Account created successfully"
                    );

                    nameField.clear();
                    emailField.clear();
                    passwordField.clear();

                } else {

                    messageLabel.setText(
                            "Email already exists"
                    );
                }

            } catch (Exception ex) {

                ex.printStackTrace();

                messageLabel.setText(
                        "Registration failed"
                );
            }
        });

        backButton.setOnAction(e -> {

            try {

                new LoginScreen().start(stage);

            } catch (Exception ex) {

                ex.printStackTrace();
            }
        });

        Scene scene = new Scene(root, 350, 320);

        stage.setTitle("Register");

        stage.setScene(scene);

        stage.show();
    }
}