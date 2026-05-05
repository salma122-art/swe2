package com.example.finance.models;

/**
 * Tracks financial goals and calculates progress toward a target amount.
 */
public class SavingsGoal {

    private int goalId;
    private String targetName;
    private double targetAmount;
    private double currentSaved;

    public SavingsGoal(int goalId, String targetName, double targetAmount) {
        this.goalId = goalId;
        this.targetName = targetName;
        this.targetAmount = targetAmount;
        this.currentSaved = 0.0;
    }

    public int getGoalId() { return goalId; }
    public String getTargetName() { return targetName; }
    public double getTargetAmount() { return targetAmount; }
    public double getCurrentSaved() { return currentSaved; }

    /**
     * Calculates the percentage of the goal achieved.
     *
     * @return A percentage value from 0 to 100.
     */
    public double calculateProgress() {
        if (targetAmount <= 0) return 0;
        return (currentSaved / targetAmount) * 100;
    }

    /**
     * Adds a specific amount to the current savings for this goal.
     *
     * @param amount The amount to be saved.
     */
    public void addSavings(double amount) {
        this.currentSaved += amount;
    }
}
