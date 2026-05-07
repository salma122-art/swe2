package com.example.finance;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.finance.controllers.FinanceController;
import com.example.finance.models.Transaction;
import com.example.finance.models.User;

public class FinanceControllerTest {

    private FinanceController financeController;

    @BeforeEach
    void setup() {

        User user = new User(
                1,
                "Test User",
                "test@test.com",
                "1234",
                0.0
        );

        financeController =
                new FinanceController(user);
    }

    // =================================================
    // ADD INCOME TEST
    // =================================================

    @Test
    void testAddIncomeTransaction() {

        boolean result =
                financeController.addTransaction(
                        1000,
                        "Income",
                        1,
                        new Date(),
                        "Salary"
                );

        assertTrue(result);

        assertEquals(
                1000,
                financeController.getBalance()
        );
    }

    // =================================================
    // ADD EXPENSE TEST
    // =================================================

    @Test
    void testAddExpenseTransaction() {

        financeController.addTransaction(
                2000,
                "Income",
                1,
                new Date(),
                "Salary"
        );

        financeController.addTransaction(
                500,
                "Expense",
                1,
                new Date(),
                "Food"
        );

        assertEquals(
                1500,
                financeController.getBalance()
        );
    }

    // =================================================
    // DELETE TEST
    // =================================================

    @Test
    void testDeleteTransaction() {

        financeController.addTransaction(
                1000,
                "Income",
                1,
                new Date(),
                "Salary"
        );

        Transaction lastTransaction =
                financeController
                        .getAllTransactions()
                        .get(
                                financeController
                                        .getAllTransactions()
                                        .size() - 1
                        );

        boolean deleted =
                financeController.deleteTransaction(
                        lastTransaction.getTransactionId()
                );

        assertTrue(deleted);

        assertEquals(
                0,
                financeController.getBalance()
        );
    }

    // =================================================
    // UPDATE TEST
    // =================================================

    @Test
    void testUpdateTransaction() {

        financeController.addTransaction(
                1000,
                "Income",
                1,
                new Date(),
                "Salary"
        );

        Transaction lastTransaction =
                financeController
                        .getAllTransactions()
                        .get(
                                financeController
                                        .getAllTransactions()
                                        .size() - 1
                        );

        boolean updated =
                financeController.updateTransaction(
                        lastTransaction.getTransactionId(),
                        2000,
                        "Updated Salary"
                );

        assertTrue(updated);

        assertEquals(
                2000,
                financeController.getBalance()
        );
    }

    // =================================================
    // INVALID AMOUNT TEST
    // =================================================

    @Test
    void testInvalidAmount() {

        boolean result =
                financeController.addTransaction(
                        -100,
                        "Income",
                        1,
                        new Date(),
                        "Invalid"
                );

        assertFalse(result);
    }
}