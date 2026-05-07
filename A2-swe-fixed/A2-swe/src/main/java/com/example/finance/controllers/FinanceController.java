package com.example.finance.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.finance.models.Budget;
import com.example.finance.models.Expense;
import com.example.finance.models.Income;
import com.example.finance.models.Transaction;
import com.example.finance.models.User;
import com.example.finance.services.BudgetService;
import com.example.finance.services.NotificationService;
import com.example.finance.services.ReportService;
import com.example.finance.utils.JsonHandler;

/**
 * Coordinates finance operations between the UI and the data layer.
 */
public class FinanceController {

    private List<Transaction> transactions =
            new ArrayList<>();

    private List<Budget> budgets =
            new ArrayList<>();

    private User user;

    // Services
    private final BudgetService budgetService =
            new BudgetService();

    private final NotificationService notificationService =
            new NotificationService();

    private final ReportService reportService =
            new ReportService();

    /**
     * Construct with authenticated user.
     */
    public FinanceController(User user) {

        this.user = user;

        JsonHandler.ensureDataDirectoryExists();

        seedDemoBudgets();
    }

    /**
     * Demo budgets.
     */
    private void seedDemoBudgets() {

        if (budgets.isEmpty()) {

            budgets.add(
                    new Budget(1, 1000, "May")
            );

            budgets.add(
                    new Budget(2, 500, "May")
            );
        }
    }

    /**
     * Add transaction.
     */
    public boolean addTransaction(double amount,
                                  String type,
                                  int categoryId,
                                  Date date,
                                  String notes) {

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

        // Update budgets
        budgetService.updateBudgetSpending(
                transactions,
                budgets
        );

        // Check limits
        if (transaction instanceof Expense) {

            boolean exceeded =
                    budgetService.isBudgetExceeded(
                            transactions,
                            budgets,
                            categoryId,
                            amount
                    );

            if (exceeded) {

                notificationService.sendBudgetAlert();
            }
        }

        // Save transactions
        JsonHandler.saveToFile(
                "data/transactions_history.json",
                transactions
        );

        // Save users list
        List<User> users =
                AppContext.authController.getAllUsers();

        JsonHandler.saveToFile(
                "data/user_profile.json",
                users
        );

        return true;
    }

    // Balance
    public double getBalance() {

        return user.getBalance();
    }

    // Reports
    public double getTotalIncome() {

        return reportService
                .calculateTotalIncome(transactions);
    }

    public double getTotalExpenses() {

        return reportService
                .calculateTotalExpenses(transactions);
    }

    public double getCalculatedBalance() {

        return reportService
                .calculateBalance(transactions);
    }

    // Transactions
    public List<Transaction> getAllTransactions() {

        return transactions;
    }

    // Budgets
    public List<Budget> getBudgets() {

        budgetService.updateBudgetSpending(
                transactions,
                budgets
        );

        return budgets;
    }

    public void addBudget(Budget budget) {

        budgets.add(budget);
    }

    // User
    public User getUser() {

        return user;
    }

    // Generate ID
    private int generateId() {

        return transactions.size() + 1;
    }
}