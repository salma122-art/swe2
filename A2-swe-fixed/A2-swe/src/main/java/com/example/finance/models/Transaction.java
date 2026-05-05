package com.example.finance.models;

import java.util.Date;

/**
 * Abstract base class for all financial movements (Income and Expense).
 * Provides common attributes like amount, date, and notes.
 */
public abstract class Transaction {

    protected int transactionId;
    protected double amount;
    protected Date date;
    protected String notes;

    public Transaction(int transactionId, double amount, Date date, String notes) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.date = date;
        this.notes = notes;
    }

    public double getAmount() { return amount; }
    public int getTransactionId() { return transactionId; }
    public Date getDate() { return date; }
    public String getNotes() { return notes; }

    /**
     * Abstract method to be implemented by subclasses to provide specific details.
     *
     * @return A formatted string with transaction information.
     */
    public abstract String getDetails();
}
