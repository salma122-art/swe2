package com.example.finance.repositories;

import com.example.finance.models.Budget;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Repository for Budget data operations.
 */
public class BudgetRepository extends BaseRepository<Budget> {

    public BudgetRepository() {
        super("budgets.json");
    }

    @Override
    protected Class<Budget> getType() {
        return Budget.class;
    }

    public Optional<Budget> findByMonth(String month) {
        return items.stream()
                .filter(b -> b.getMonth().equalsIgnoreCase(month))
                .findFirst();
    }

    public Optional<Budget> findById(int budgetId) {
        return items.stream()
                .filter(b -> b.getBudgetId() == budgetId)
                .findFirst();
    }

    public List<Budget> getOverLimitBudgets() {
        List<Budget> overLimit = new ArrayList<>();
        for (Budget b : items) {
            if (b.checkLimit()) {
                overLimit.add(b);
            }
        }
        return overLimit;
    }
}
