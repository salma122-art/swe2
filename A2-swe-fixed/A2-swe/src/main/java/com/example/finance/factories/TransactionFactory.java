package com.example.finance.factories;

import com.example.finance.models.Expense;
import com.example.finance.models.Income;
import com.example.finance.models.Transaction;

import java.util.Date;

/**
 * Factory for creating Transaction instances.
 * SOLID: Single Responsibility — keeps construction logic in one place.
 */
public class TransactionFactory {

    private TransactionFactory() {
        // factory class
    }

    public static Transaction createIncome(int id, double amount, Date date, String notes, String source) {
        return new Income(id, amount, date, notes, source);
    }

    public static Transaction createExpense(int id, double amount, Date date, String notes, int categoryId) {
        return new Expense(id, amount, date, notes, categoryId);
    }
}
