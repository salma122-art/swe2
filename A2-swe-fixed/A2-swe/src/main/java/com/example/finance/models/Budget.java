package com.example.finance.models;

/**
 * Manages monthly spending limits and monitors current expenditures.
 */
public class Budget {

    private int budgetId;
    private double limitAmount;
    private double currentSpending;
    private String month;

    public Budget() {
    }

    public Budget(int budgetId, double limitAmount, String month) {
        this.budgetId = budgetId;
        this.limitAmount = limitAmount;
        this.month = month;
        this.currentSpending = 0.0;
    }

    public int getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(int budgetId) {
        this.budgetId = budgetId;
    }

    public double getLimitAmount() {
        return limitAmount;
    }

    public void setLimitAmount(double limitAmount) {
        this.limitAmount = limitAmount;
    }

    public double getCurrentSpending() {
        return currentSpending;
    }

    public void setCurrentSpending(double currentSpending) {
        this.currentSpending = currentSpending;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    /**
     * Add expense amount to current spending.
     */
    public void addSpending(double amount) {
        if (amount > 0) {
            this.currentSpending += amount;
        }
    }

    /**
     * Checks if budget exceeded limit.
     */
    public boolean checkLimit() {
        return currentSpending >= limitAmount;
    }

    /**
     * Remaining budget.
     */
    public double getRemainingAmount() {
        return limitAmount - currentSpending;
    }
}