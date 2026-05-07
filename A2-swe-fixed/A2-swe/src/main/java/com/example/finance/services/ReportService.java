package com.example.finance.services;

import java.util.ArrayList;
import java.util.List;

import com.example.finance.models.Expense;
import com.example.finance.models.Income;
import com.example.finance.models.Transaction;

public class ReportService {

    public double calculateTotalIncome(List<Transaction> transactions) {

        double total = 0;

        if (transactions == null) {
            return 0;
        }

        for (Transaction transaction : transactions) {

            if (transaction instanceof Income) {
                total += transaction.getAmount();
            }
        }

        return total;
    }

    public double calculateTotalExpenses(List<Transaction> transactions) {

        double total = 0;

        if (transactions == null) {
            return 0;
        }

        for (Transaction transaction : transactions) {

            if (transaction instanceof Expense) {
                total += transaction.getAmount();
            }
        }

        return total;
    }

    public double calculateBalance(List<Transaction> transactions) {

        return calculateTotalIncome(transactions)
                - calculateTotalExpenses(transactions);
    }

    /**
     * Returns all income transactions.
     */
    public List<Transaction> getAllIncomeTransactions(
            List<Transaction> transactions) {

        List<Transaction> incomeTransactions = new ArrayList<>();

        if (transactions == null) {
            return incomeTransactions;
        }

        for (Transaction transaction : transactions) {

            if (transaction instanceof Income) {

                incomeTransactions.add(transaction);
            }
        }

        return incomeTransactions;
    }

    /**
     * Returns all expense transactions.
     */
    public List<Transaction> getAllExpenseTransactions(
            List<Transaction> transactions) {

        List<Transaction> expenseTransactions = new ArrayList<>();

        if (transactions == null) {
            return expenseTransactions;
        }

        for (Transaction transaction : transactions) {

            if (transaction instanceof Expense) {

                expenseTransactions.add(transaction);
            }
        }

        return expenseTransactions;
    }

    /**
     * Returns number of transactions.
     */
    public int getTransactionCount(List<Transaction> transactions) {

        if (transactions == null) {
            return 0;
        }

        return transactions.size();
    }
}