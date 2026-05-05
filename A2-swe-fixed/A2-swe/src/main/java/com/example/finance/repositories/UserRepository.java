package com.example.finance.repositories;

import com.example.finance.models.User;
import com.example.finance.utils.JsonHandler;

/**
 * Repository for User data operations.
 */
public class UserRepository {

    private static final String FILE_NAME = "data/user_profile.json";
    private User currentUser;

    public UserRepository() {
        load();
    }

    public void load() {
        currentUser = JsonHandler.loadFromFile(FILE_NAME, User.class);
        if (currentUser == null) {
            currentUser = new User(1, "Default User", "user@email.com", 0.0);
            save();
        }
    }

    public void save() {
        JsonHandler.ensureDataDirectoryExists();
        JsonHandler.saveToFile(FILE_NAME, currentUser);
    }

    public User getUser() {
        return currentUser;
    }

    public void updateUser(User user) {
        this.currentUser = user;
        save();
    }

    public void updateBalance(double amount) {
        currentUser.updateBalance(amount);
        save();
    }

    public double getBalance() {
        return currentUser.getBalance();
    }

    public boolean authenticate(String email) {
        return currentUser.getEmail().equals(email);
    }

    public void register(User user) {
        this.currentUser = user;
        save();
    }
}
