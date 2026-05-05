package com.example.finance.controllers;

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

    private final AuthController authController = new AuthController();

    @FXML
    public void handleLogin() {
        String email = emailField.getText();
        String password = passwordField.getText();

        boolean success = authController.authenticate(email, password);

        if (success) {
            statusLabel.setText("Login Successful");
            System.out.println("User logged in successfully");
        } else {
            statusLabel.setText("Invalid credentials");
        }
    }
}
