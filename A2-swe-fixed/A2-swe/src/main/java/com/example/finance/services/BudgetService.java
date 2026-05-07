package com.example.finance.service;

import com.example.finance.models.Budget;
import com.example.finance.models.Expense;
import com.example.finance.models.Transaction;

import java.util.List;

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
                return totalExpenses > budget.getLimitAmount();
            }
        }

        return false;
    }
}
