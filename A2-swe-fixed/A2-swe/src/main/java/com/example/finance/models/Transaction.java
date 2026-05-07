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

    public Transaction() {
    }

    public Transaction(int transactionId, double amount, Date date, String notes) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.date = date;
        this.notes = notes;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * Abstract method to be implemented by subclasses to provide specific details.
     *
     * @return A formatted string with transaction information.
     */
    public abstract String getDetails();
}