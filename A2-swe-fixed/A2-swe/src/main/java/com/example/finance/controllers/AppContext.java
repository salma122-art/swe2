package com.example.finance.controllers;

import com.example.finance.models.User;

/**
 * Lightweight service locator for controllers shared across UI screens.
 *
 * AuthController is created up front. FinanceController must be initialized
 * AFTER successful login via {@link #initFinanceController(User)} because
 * it requires the authenticated user.
 */
public final class AppContext {

    public static final AuthController authController = new AuthController();
    public static FinanceController financeController;

    private AppContext() {
        // utility class
    }

    /**
     * Wire up the FinanceController for the authenticated user.
     * Call from the login flow after credentials are verified.
     */
    public static void initFinanceController(User user) {
        financeController = new FinanceController(user);
    }
}
