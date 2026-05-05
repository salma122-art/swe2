package com.example.finance.models;

import java.util.Date;

/**
 * Represents money spent by the user on specific categories.
 * Inherits from the Transaction class.
 */
public class Expense extends Transaction {

    private int categoryId;

    public Expense(int transactionId, double amount, Date date, String notes, int categoryId) {
        super(transactionId, amount, date, notes);
        this.categoryId = categoryId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    @Override
    public String getDetails() {
        return "Expense [ID: " + transactionId + "] | Category: " + categoryId
                + " | Amount: " + amount + " | Date: " + date;
    }
}
