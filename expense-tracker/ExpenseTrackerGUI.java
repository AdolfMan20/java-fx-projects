import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.File;
import java.time.LocalDate;

public class ExpenseTrackerGUI extends Application {

    private final ExpenseManager expenseManager = new ExpenseManager();
    private final File fileSavings = new File("Expense Tracker V1.csv");


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Expense Tracker");

        // UI Elements
        TextField descriptionField = new TextField();
        TextField amountField = new TextField();

        ComboBox<String> categoryComboBox = new ComboBox<>();
        categoryComboBox.getItems().addAll(
                "Groceries", "Eating Out", "Coffee", "Clothes", "Others", "Cosmetics"
        );
        categoryComboBox.getSelectionModel().selectFirst();

        Button addButton = new Button("Add Expense");
        Button saveButton = new Button("Save to File");

        Label messageLabel = new Label();

        // Layout
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(10);
        grid.setHgap(10);

        grid.add(new Label("Description:"), 0, 0);
        grid.add(descriptionField, 1, 0);

        grid.add(new Label("Amount:"), 0, 1);
        grid.add(amountField, 1, 1);

        grid.add(new Label("Category:"), 0, 2);
        grid.add(categoryComboBox, 1, 2);

        grid.add(addButton, 0, 3);
        grid.add(saveButton, 1, 3);
        grid.add(messageLabel, 0, 4, 2, 1);

        // Actions
        addButton.setOnAction(e -> {
            try {
                String description = descriptionField.getText();
                double amount = Double.parseDouble(amountField.getText());
                String category = categoryComboBox.getValue();
                LocalDate date = LocalDate.now();

                Expense expense = new Expense(description, amount, category, date);
                expenseManager.addExpense(expense);

                messageLabel.setText("Expense added successfully.");
                descriptionField.clear();
                amountField.clear();
            } catch (NumberFormatException ex) {
                messageLabel.setText("Please enter a valid number for amount.");
            }
        });

        saveButton.setOnAction(e -> {
            expenseManager.saveToFile(fileSavings);
            messageLabel.setText("Expenses saved to file.");
        });

        Scene scene = new Scene(grid, 400, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}


