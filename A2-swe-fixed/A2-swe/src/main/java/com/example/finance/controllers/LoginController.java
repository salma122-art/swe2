package com.example.finance.controllers;

import com.example.finance.models.User;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML controller for the login view.
 */
public class LoginController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label statusLabel;

    private final AuthController authController =
            new AuthController();

    @FXML
    public void handleLogin() {

        String email =
                emailField.getText().trim();

        String password =
                passwordField.getText().trim();

        boolean success =
                authController.authenticate(
                        email,
                        password
                );

        if (success) {

            User user =
                    authController.getUser(email);

            AppContext.initFinanceController(user);

            statusLabel.setText(
                    "Login Successful"
            );

            System.out.println(
                    "User logged in successfully"
            );

        } else {

            statusLabel.setText(
                    "Invalid credentials"
            );
        }
    }
}