package com.expensetrackerfx;

import java.io.File;
import java.time.LocalDate;
import java.util.*;

public class ExpenseTrackerConsole {
    public static final ExpenseManager expenseManager = new ExpenseManager();

    public static String categoryList(){
        Map<Integer, String> categoryList = new HashMap<>();
        categoryList.put(1,"Groceries");
        categoryList.put(2,"Eating Out");
        categoryList.put(3,"Coffee");
        categoryList.put(4,"Clothes");
        categoryList.put(5,"Others");
        categoryList.put(6,"Cosmetics");
        Scanner input = new Scanner(System.in);

        System.out.println("Available Categories:");
        for (Map.Entry<Integer, String> entry : categoryList.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        System.out.println("Type the Category Id: ");
        try {
            int id = input.nextInt();
            input.nextLine();
            if (categoryList.containsKey(id)) {
                System.out.println(categoryList.get(id));
                return categoryList.get(id); // Return the category name
            } else {
                System.out.println("Invalid category ID. Using default category: 'Others'");
                return "Others";
            }
        } catch (Exception e) {
            System.out.println("Please type a valid number. Using default category: 'Others'");
            return "Others";
        }
    }
    public static void addExpenseDinamically(){
        Scanner input = new Scanner(System.in);

        System.out.println("Description");
        String description = input.nextLine();

        System.out.println("Amount");
        double amount = input.nextDouble();
        input.nextLine();

        System.out.println("Category(choose)");
        String category = categoryList();
        LocalDate date = LocalDate.now();

        Expense expense = new Expense(description, amount, category, date);
        expenseManager.addExpense(expense);
    }
    public static void main(String[] args) {
        File fileSavings = new File("Expense Tracker.csv");
        addExpenseDinamically();
        expenseManager.getTotalExpense();
        expenseManager.saveToFile(fileSavings);

    }
}
