package com.example.finance.services;

import java.util.List;

import com.example.finance.models.Budget;
import com.example.finance.models.Expense;
import com.example.finance.models.Transaction;

public class BudgetService {

    public boolean isBudgetExceeded(List<Transaction> transactions,
                                    List<Budget> budgets,
                                    int categoryId,
                                    double newExpenseAmount) {

        double totalExpenses = newExpenseAmount;

        for (Transaction transaction : transactions) {

            if (transaction instanceof Expense expense) {

                if (expense.getCategoryId() == categoryId) {
                    totalExpenses += expense.getAmount();
                }
            }
        }

        for (Budget budget : budgets) {

            if (budget.getBudgetId() == categoryId) {

                budget.setCurrentSpending(totalExpenses);

                return totalExpenses > budget.getLimitAmount();
            }
        }

        return false;
    }

    /**
     * Updates the current spending for all budgets.
     */
    public void updateBudgetSpending(List<Transaction> transactions,
                                     List<Budget> budgets) {

        for (Budget budget : budgets) {

            double totalExpenses = 0;

            for (Transaction transaction : transactions) {

                if (transaction instanceof Expense expense) {

                    if (expense.getCategoryId() == budget.getBudgetId()) {

                        totalExpenses += expense.getAmount();
                    }
                }
            }

            budget.setCurrentSpending(totalExpenses);
        }
    }

    /**
     * Returns remaining amount for a specific budget.
     */
    public double getRemainingBudget(Budget budget) {

        return budget.getRemainingAmount();
    }
}