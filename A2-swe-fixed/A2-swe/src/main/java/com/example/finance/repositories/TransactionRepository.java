package com.example.finance.repositories;

import com.example.finance.models.Expense;
import com.example.finance.models.Income;
import com.example.finance.models.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Repository for Transaction data operations.
 * Handles Income and Expense transactions stored in transactions.json.
 *
 * Note: Gson cannot deserialize an abstract type without a custom adapter.
 * If you persist mixed Income/Expense lists, you will need a RuntimeTypeAdapterFactory.
 * For now, this works in-memory and round-trips concrete subclasses written by us.
 */
public class TransactionRepository extends BaseRepository<Transaction> {

    public TransactionRepository() {
        super("transactions.json");
    }

    @Override
    protected Class<Transaction> getType() {
        return Transaction.class;
    }

    public List<Transaction> getIncomes() {
        List<Transaction> incomes = new ArrayList<>();
        for (Transaction t : items) {
            if (t instanceof Income) {
                incomes.add(t);
            }
        }
        return incomes;
    }

    public List<Transaction> getExpenses() {
        List<Transaction> expenses = new ArrayList<>();
        for (Transaction t : items) {
            if (t instanceof Expense) {
                expenses.add(t);
            }
        }
        return expenses;
    }

    public double getTotalIncome() {
        double total = 0;
        for (Transaction t : getIncomes()) {
            total += t.getAmount();
        }
        return total;
    }

    public double getTotalExpense() {
        double total = 0;
        for (Transaction t : getExpenses()) {
            total += t.getAmount();
        }
        return total;
    }

    public double getNetBalance() {
        return getTotalIncome() - getTotalExpense();
    }

    public List<Expense> getExpensesByCategory(int categoryId) {
        List<Expense> result = new ArrayList<>();
        for (Transaction t : items) {
            if (t instanceof Expense e && e.getCategoryId() == categoryId) {
                result.add(e);
            }
        }
        return result;
    }

    public List<Transaction> getExpensesByMonth(String month) {
        List<Transaction> result = new ArrayList<>();
        for (Transaction t : getExpenses()) {
            if (t.getDate() != null) {
                result.add(t);
            }
        }
        return result;
    }

    public void addTransaction(Transaction transaction) {
        add(transaction);
    }

    public void deleteTransaction(int id) {
        items.removeIf(t -> t.getTransactionId() == id);
        save();
    }

    public Transaction getById(int id) {
        for (Transaction t : items) {
            if (t.getTransactionId() == id) {
                return t;
            }
        }
        return null;
    }
}
