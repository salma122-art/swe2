package com.example.finance;

import com.example.finance.controllers.FinanceController;
import com.example.finance.models.User;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class FinanceControllerTest {

    @Test
    void incomeIncreasesBalance() {
        User user = new User(1, "Test", "test@example.com", 100.0);
        FinanceController fc = new FinanceController(user);

        boolean ok = fc.addTransaction(50.0, "Income", 0, new Date(), "salary");

        assertTrue(ok);
        assertEquals(150.0, fc.getBalance(), 0.0001);
    }

    @Test
    void expenseDecreasesBalance() {
        User user = new User(1, "Test", "test@example.com", 100.0);
        FinanceController fc = new FinanceController(user);

        boolean ok = fc.addTransaction(30.0, "Expense", 1, new Date(), "lunch");

        assertTrue(ok);
        assertEquals(70.0, fc.getBalance(), 0.0001);
    }

    @Test
    void rejectsNonPositiveAmount() {
        User user = new User(1, "Test", "test@example.com", 100.0);
        FinanceController fc = new FinanceController(user);

        assertFalse(fc.addTransaction(0, "Income", 0, new Date(), "x"));
        assertFalse(fc.addTransaction(-5, "Expense", 0, new Date(), "x"));
        assertEquals(100.0, fc.getBalance(), 0.0001);
    }

    @Test
    void rejectsUnknownTransactionType() {
        User user = new User(1, "Test", "test@example.com", 100.0);
        FinanceController fc = new FinanceController(user);

        assertFalse(fc.addTransaction(10, "Donation", 0, new Date(), "x"));
        assertEquals(100.0, fc.getBalance(), 0.0001);
    }
}
