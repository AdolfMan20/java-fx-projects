package com.expensetrackerfx;

import java.time.LocalDate;
import java.util.List;

public class Expense {
    private String description;
    private double amount;
    private String category;
    private LocalDate date;

    public Expense(String description, double amount, String categories, LocalDate date){
        this.description = description;
        this.amount = amount;
        this.category = categories;
        this.date = date;
    }

    public Expense(){
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "description='" + description + '\'' +
                ", amount=" + amount +
                ", categories='" + category + '\'' +
                ", date=" + date +
                '}';
    }
}
