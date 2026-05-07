package com.example.finance.controllers;

import com.example.finance.models.User;
import com.example.finance.utils.JsonHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles authentication logic (login and register).
 */
public class AuthController {

    private static List<User> users = new ArrayList<>();

    public AuthController() {

        JsonHandler.ensureDataDirectoryExists();

        List<User> loadedUsers =
                JsonHandler.loadUsersFromFile(
                        "data/user_profile.json"
                );

        if (loadedUsers != null
                && !loadedUsers.isEmpty()) {

            users = loadedUsers;

        } else {

            users.add(
                    new User(
                            1,
                            "Ahmed Ali",
                            "ahmed@gmail.com",
                            "password",
                            1000.0
                    )
            );

            JsonHandler.saveToFile(
                    "data/user_profile.json",
                    users
            );
        }
    }

    /**
     * Authenticate using email and password.
     */
    public boolean authenticate(String email,
                                String password) {

        for (User user : users) {

            if (user.getEmail().equalsIgnoreCase(email)
                    && user.getPassword().equals(password)) {

                return true;
            }
        }

        return false;
    }

    /**
     * Register new user.
     */
    public boolean register(User user) {

        for (User existingUser : users) {

            if (existingUser.getEmail()
                    .equalsIgnoreCase(user.getEmail())) {

                return false;
            }
        }

        users.add(user);

        JsonHandler.saveToFile(
                "data/user_profile.json",
                users
        );

        return true;
    }

    /**
     * Get user by email.
     */
    public User getUser(String email) {

        for (User user : users) {

            if (user.getEmail()
                    .equalsIgnoreCase(email)) {

                return user;
            }
        }

        return null;
    }

    /**
     * Returns all users.
     */
    public List<User> getAllUsers() {

        return users;
    }
}