package com.example.finance.service;

import com.example.finance.models.Expense;
import com.example.finance.models.Income;
import com.example.finance.models.Transaction;

import java.util.List;

public class ReportService {

    public double calculateTotalIncome(List<Transaction> transactions) {

        double total = 0;

        for (Transaction transaction : transactions) {

            if (transaction instanceof Income) {
                total += transaction.getAmount();
            }
        }

        return total;
    }

    public double calculateTotalExpenses(List<Transaction> transactions) {

        double total = 0;

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
}
