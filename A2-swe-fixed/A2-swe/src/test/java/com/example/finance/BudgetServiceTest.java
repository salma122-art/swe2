package com.example.finance;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.example.finance.models.Budget;
import com.example.finance.models.Expense;
import com.example.finance.models.Transaction;
import com.example.finance.services.BudgetService;

public class BudgetServiceTest {

    // =================================================
    // BUDGET EXCEEDED TEST
    // =================================================

    @Test
    void testBudgetExceeded() {

        BudgetService budgetService =
                new BudgetService();

        List<Transaction> transactions =
                new ArrayList<>();

        List<Budget> budgets =
                new ArrayList<>();

        Budget budget =
                new Budget(
                        1,
                        1000,
                        "May"
                );

        budgets.add(budget);

        transactions.add(
                new Expense(
                        1,
                        800,
                        new Date(),
                        "Food",
                        1
                )
        );

        boolean exceeded =
                budgetService.isBudgetExceeded(
                        transactions,
                        budgets,
                        1,
                        500
                );

        assertTrue(exceeded);
    }

    // =================================================
    // REMAINING BUDGET TEST
    // =================================================

    @Test
    void testRemainingBudget() {

        Budget budget =
                new Budget(
                        1,
                        2000,
                        "May"
                );

        budget.setCurrentSpending(500);

        BudgetService budgetService =
                new BudgetService();

        double remaining =
                budgetService.getRemainingBudget(
                        budget
                );

        assertEquals(
                1500,
                remaining
        );
    }

    // =================================================
    // UPDATE SPENDING TEST
    // =================================================

    @Test
    void testUpdateBudgetSpending() {

        BudgetService budgetService =
                new BudgetService();

        List<Transaction> transactions =
                new ArrayList<>();

        List<Budget> budgets =
                new ArrayList<>();

        Budget budget =
                new Budget(
                        1,
                        3000,
                        "May"
                );

        budgets.add(budget);

        transactions.add(
                new Expense(
                        1,
                        700,
                        new Date(),
                        "Shopping",
                        1
                )
        );

        transactions.add(
                new Expense(
                        2,
                        300,
                        new Date(),
                        "Food",
                        1
                )
        );

        budgetService.updateBudgetSpending(
                transactions,
                budgets
        );

        assertEquals(
                1000,
                budget.getCurrentSpending()
        );
    }
}