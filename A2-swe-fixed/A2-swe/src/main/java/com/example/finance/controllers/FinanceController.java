package com.example.finance.controllers;
import com.example.finance.models.Budget;
import com.example.finance.models.Expense;
import com.example.finance.models.Income;
import com.example.finance.models.Transaction;
import com.example.finance.models.User;
import com.example.finance.service.BudgetService;
import com.example.finance.service.NotificationService;
import com.example.finance.service.ReportService;
import com.example.finance.utils.JsonHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Coordinates finance operations between the UI and the data layer.
 * Holds an in-memory view of transactions and budgets for the active user
 * and persists transactions and the user profile to the data/ directory.
 */
public class FinanceController {

    private final List<Transaction> transactions = new ArrayList<>();
    private final List<Budget> budgets = new ArrayList<>();

    private User user;

    // Services
    private final BudgetService budgetService = new BudgetService();
    private final NotificationService notificationService = new NotificationService();
    private final ReportService reportService = new ReportService();

    /**
     * Construct with an authenticated user.
     */
    public FinanceController(User user) {
        this.user = user;
        seedDemoBudgets();
    }

    private void seedDemoBudgets() {
        budgets.add(new Budget(1, 1000, "May"));
        budgets.add(new Budget(2, 500, "May"));
    }

    /**
     * Add an income or expense transaction.
     *
     * @return true if the transaction was added, false on validation failure.
     */
    public boolean addTransaction(double amount,
                                  String type,
                                  int categoryId,
                                  Date date,
                                  String notes) {

        // Validation
        if (amount <= 0 || type == null) {
            return false;
        }

        Transaction transaction;

        // Income
        if (type.equalsIgnoreCase("Income")) {

            transaction = new Income(
                    generateId(),
                    amount,
                    date,
                    notes,
                    "Manual Income"
            );

            user.updateBalance(amount);

        }

        // Expense
        else if (type.equalsIgnoreCase("Expense")) {

            transaction = new Expense(
                    generateId(),
                    amount,
                    date,
                    notes,
                    categoryId
            );

            user.updateBalance(-amount);

        } else {
            return false;
        }

        // Add transaction
        transactions.add(transaction);

        // Check budget only for expenses
        if (transaction instanceof Expense) {

            boolean exceeded = budgetService.isBudgetExceeded(
                    transactions,
                    budgets,
                    categoryId,
                    amount
            );

            if (exceeded) {
                notificationService.sendBudgetAlert();
            }
        }

        // Save data
        JsonHandler.ensureDataDirectoryExists();

        JsonHandler.saveToFile(
                "data/transactions_history.json",
                transactions
        );

        JsonHandler.saveToFile(
                "data/user_profile.json",
                user
        );

        return true;
    }

    // Current Balance
    public double getBalance() {
        return user.getBalance();
    }

    // Reports
    public double getTotalIncome() {
        return reportService.calculateTotalIncome(transactions);
    }

    public double getTotalExpenses() {
        return reportService.calculateTotalExpenses(transactions);
    }

    public double getCalculatedBalance() {
        return reportService.calculateBalance(transactions);
    }

    // Transactions
    public List<Transaction> getAllTransactions() {
        return transactions;
    }

    // Budgets
    public List<Budget> getBudgets() {
        return budgets;
    }

    public void addBudget(Budget budget) {
        budgets.add(budget);
    }

    // User
    public User getUser() {
        return user;
    }

    // Generate transaction ID
    private int generateId() {
        return transactions.size() + 1;
    }
}
