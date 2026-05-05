package com.example.finance.models;

import java.util.Date;

/**
 * Represents money earned by the user from various sources.
 * Inherits from the Transaction class.
 */
public class Income extends Transaction {

    private String source;

    public Income(int transactionId, double amount, Date date, String notes, String source) {
        super(transactionId, amount, date, notes);
        this.source = source;
    }

    public String getSource() {
        return source;
    }

    /**
     * Overrides getDetails to include the income source.
     *
     * @return Formatted details of the income.
     */
    @Override
    public String getDetails() {
        return "Income Source: " + source + " | Amount: " + amount + " | Date: " + date;
    }
}
