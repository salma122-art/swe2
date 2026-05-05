package com.example.finance.controllers;

import com.example.finance.models.User;
import com.example.finance.utils.JsonHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles authentication logic (login and register).
 * Demo-only: passwords are not actually checked.
 */
public class AuthController {

    private static final List<User> users = new ArrayList<>();

    public AuthController() {
        if (users.isEmpty()) {
            users.add(new User(1, "Ahmed Ali", "ahmed@gmail.com", 1000.0));
        }
    }

    /**
     * Authenticate by email only (demo project scope).
     * In production this would verify a hashed password.
     */
    public boolean authenticate(String email, String password) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public void register(User user) {
        users.add(user);
        JsonHandler.ensureDataDirectoryExists();
        JsonHandler.saveToFile("data/user_profile.json", users);
    }

    public User getUser(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }
}
