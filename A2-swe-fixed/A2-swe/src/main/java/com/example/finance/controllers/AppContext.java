package com.example.finance.controllers;

import com.example.finance.models.User;

/**
 * Lightweight service locator for controllers shared across UI screens.
 */
public final class AppContext {

    public static final AuthController authController =
            new AuthController();

    public static FinanceController financeController;

    private AppContext() {
    }

    /**
     * Initialize finance controller after login.
     */
    public static void initFinanceController(User user) {

        financeController =
                new FinanceController(user);
    }
}