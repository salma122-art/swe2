package com.example.finance.repositories;

import java.util.ArrayList;
import java.util.List;

import com.example.finance.models.User;
import com.example.finance.utils.JsonHandler;
import com.example.finance.utils.PasswordUtil;

/**
 * Repository for User data operations.
 */
public class UserRepository {

    private static final String FILE_NAME =
            "data/user_profile.json";

    private List<User> users =
            new ArrayList<>();

    private User currentUser;

    public UserRepository() {

        load();
    }

    /**
     * Load users from JSON.
     */
    public void load() {

        users =
                JsonHandler.loadUsersFromFile(
                        FILE_NAME
                );

        if (users == null
                || users.isEmpty()) {

            User defaultUser =
                    new User(
                            1,
                            "Default User",
                            "user@email.com",
                            PasswordUtil.hashPassword(
                                    "password"
                            ),
                            0.0
                    );

            users = new ArrayList<>();

            users.add(defaultUser);

            currentUser = defaultUser;

            save();

        } else {

            currentUser = users.get(0);
        }
    }

    /**
     * Save users.
     */
    public void save() {

        JsonHandler.ensureDataDirectoryExists();

        JsonHandler.saveToFile(
                FILE_NAME,
                users
        );
    }

    public User getUser() {

        return currentUser;
    }

    public List<User> getAllUsers() {

        return users;
    }

    public void updateUser(User user) {

        this.currentUser = user;

        save();
    }

    public void updateBalance(double amount) {

        if (currentUser != null) {

            currentUser.updateBalance(amount);

            save();
        }
    }

    public double getBalance() {

        if (currentUser == null) {

            return 0;
        }

        return currentUser.getBalance();
    }

    /**
     * Authenticate user.
     */
    public boolean authenticate(String email,
                                String password) {

        String hashedPassword =
                PasswordUtil.hashPassword(password);

        for (User user : users) {

            if (user.getEmail()
                    .equalsIgnoreCase(email)
                    && user.getPassword()
                    .equals(hashedPassword)) {

                currentUser = user;

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
                    .equalsIgnoreCase(
                            user.getEmail()
                    )) {

                return false;
            }
        }

        user.setPassword(
                PasswordUtil.hashPassword(
                        user.getPassword()
                )
        );

        users.add(user);

        save();

        return true;
    }
}