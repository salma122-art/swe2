package com.example.finance.models;

/**
 * Manages monthly spending limits and monitors current expenditures.
 */
public class Budget {

    private int budgetId;
    private double limitAmount;
    private double currentSpending;
    private String month;

    public Budget(int budgetId, double limitAmount, String month) {
        this.budgetId = budgetId;
        this.limitAmount = limitAmount;
        this.month = month;
        this.currentSpending = 0.0;
    }

    public int getBudgetId() { return budgetId; }
    public double getLimitAmount() { return limitAmount; }
    public double getCurrentSpending() { return currentSpending; }
    public String getMonth() { return month; }

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
        return currentSpending > limitAmount;
    }

    /**
     * Remaining budget.
     */
    public double getRemainingAmount() {
        return limitAmount - currentSpending;
    }
}
