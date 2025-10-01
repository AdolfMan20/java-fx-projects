package com.expensetrackerfx;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class ExpenseManager {
    private List<Expense> expenses = new ArrayList<>();
    private Set<String> categories = new HashSet<>();

    public ExpenseManager(List<Expense> expenses){
        this.expenses = expenses;
    }

    public ExpenseManager(){
    }

    public List<Expense> getExpenses(){
        return expenses;
    }

    public void setExpenses(List<Expense> expenses){
        this.expenses = expenses;
    }

    public Set<String> getCategories() {
        return categories;
    }

    public void setCategories(Set<String> categories) {
        this.categories = categories;
    }

    public void addExpense(Expense expenseItem){
        expenses.add(expenseItem);
        System.out.println("Item added to the list");
    }

    public void removeExpense(Expense expenseItem){
        expenses.remove(expenseItem);
        System.out.println("Item removed from the list");
    }

    public double getTotalExpense(){
        double sum = 0;
        for (Expense expense : expenses) {
            sum = sum + expense.getAmount();
        }
        System.out.println("The total amount of Expenses: "+sum);
        return sum;
    }

    public void addCategory(String categoryName){
        System.out.println("Please put the name of the new category: ");
        categories.add(categoryName);
    }

    public void removeCategory(String category){
        if(categories.remove(category)){
            System.out.println("Category removed");
        }else {
            System.out.println("No category was found by that name");
        }
    }


    public void saveToFile(File theFile){
        try{
            BufferedWriter bufferedWriterObj = new BufferedWriter(new FileWriter(theFile, true));
            for (Expense e : expenses){
                String line = e.getDescription()+", "+e.getAmount()+", "+e.getCategory()+", "+e.getDate();
                bufferedWriterObj.write(line);
                bufferedWriterObj.newLine();
            }
            System.out.println("The info was written into the file with success.");
            bufferedWriterObj.close();
        } catch (IOException e) {
            System.out.println("An error occurred during the writing process. \n"+e.getMessage());
        }
    }

    public void loadFromFile(File thFile){
        try{
            BufferedReader bufferedReaderObj = new BufferedReader(new FileReader(thFile));
            String line;
            System.out.println("The info from the file: ");
            while ((line = bufferedReaderObj.readLine()) != null){
                System.out.println(line);
                String[] parts = line.split(",");
                if (parts.length == 4){
                    String description = parts[0];
                    double amount = Double.parseDouble(parts[1]);
                    String category = parts[2];
                    LocalDate date = LocalDate.parse(parts[3]);
                    expenses.add(new Expense(description, amount, category, date));
                }
            }
            System.out.println("Info from the file inserted correctly.");
        } catch (IOException e) {
            System.out.println("An error occurred during the reading process. \n"+e.getMessage());
        }
    }
}
