package com.example.finance.models;

/**
 * Defines a classification for transactions (e.g., Food, Rent).
 */
public class Category {

    private int categoryId;
    private String name;
    private boolean isCustom;

    public Category() {
    }

    public Category(int categoryId, String name, boolean isCustom) {
        this.categoryId = categoryId;
        this.name = name;
        this.isCustom = isCustom;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public boolean isCustom() {
        return isCustom;
    }

    public void setCustom(boolean custom) {
        isCustom = custom;
    }

    public void setName(String name) {
        this.name = name;
    }
}