package com.example.finance.models;

/**
 * Represents a system user and manages their total financial balance.
 */
public class User {

    private int userId;
    private String name;
    private String email;
    private String password;
    private double totalBalance;

    public User() {
    }

    public User(int userId, String name, String email,
                String password, double totalBalance) {

        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.totalBalance = totalBalance;
    }

    /**
     * Updates the user's total balance by adding or subtracting an amount.
     *
     * @param amount The value to add (positive for income, negative for expense).
     */
    public void updateBalance(double amount) {
        this.totalBalance += amount;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getBalance() {
        return totalBalance;
    }

    public void setBalance(double totalBalance) {
        this.totalBalance = totalBalance;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}