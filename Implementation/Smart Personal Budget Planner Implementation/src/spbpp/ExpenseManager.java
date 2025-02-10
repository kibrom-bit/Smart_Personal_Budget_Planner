package spbpp;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ExpenseManager {
    private static final String EXPENSE_FILE_PREFIX = "expenses_";
    private static final int AMOUNT_INDEX = 1; // Index for the amount in the expense data
    private static final int CATEGORY_INDEX = 2; // Index for the category in the expense data

    public void addExpense(String year, String month, String expenseData) {
        saveToFile(EXPENSE_FILE_PREFIX + year + "_" + month + ".txt", expenseData, true);
        System.out.println("Expense added: " + expenseData);
    }

    public void editExpense(String year, String month, List<String> expenses, int index, String newExpenseData) {
        if (index < 0 || index >= expenses.size()) {
            System.out.println("Invalid expense index.");
            return;
        }
        
        expenses.set(index, newExpenseData);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(EXPENSE_FILE_PREFIX + year + "_" + month + ".txt"))) {
            for (String expense : expenses) {
                writer.write(expense + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving expenses: " + e.getMessage());
        }
        System.out.println("Expense updated: " + newExpenseData);
    }

    public List<String> loadExpenses(String year, String month) {
        List<String> expenses = new ArrayList<>();
        String expenseFile = EXPENSE_FILE_PREFIX + year + "_" + month + ".txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(expenseFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                expenses.add(line);
            }
        } catch (IOException e) {
            System.out.println("No expenses to load or file not found!");
        }
        return expenses;
    }

    private void saveToFile(String filename, String data, boolean append) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, append))) {
            writer.write(data + "\n");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    public int calculateTotalExpenses(String year, String month) {
        int total = 0;
        String expenseFile = EXPENSE_FILE_PREFIX + year + "_" + month + ".txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(expenseFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > AMOUNT_INDEX) {
                    try {
                        total += Integer.parseInt(parts[AMOUNT_INDEX]); // Add the amount
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid amount format in expense: " + line);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading expenses file: " + e.getMessage());
        }
        return total;
    }

    public int getTotalExpenses(String year, String month) {
    return calculateTotalExpenses(year, month);
}
}