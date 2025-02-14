package spbpp;

import java.io.*;
import java.util.OptionalInt;

public class BudgetManager {
    private static final String BUDGET_FILE_PREFIX = "budget_";
    public int monthlyBudget;

    public OptionalInt loadBudget(String year, String month) {
        String budgetFile = BUDGET_FILE_PREFIX + year + "_" + month + ".txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(budgetFile))) {
            monthlyBudget = Integer.parseInt(reader.readLine());
            System.out.println("Budget loaded: $" + monthlyBudget);
        } catch (IOException | NumberFormatException e) {
            monthlyBudget = 0;
            System.out.println("No previous budget found. Starting with $0 budget.");
        }
         return OptionalInt.empty();
    }

    public void setBudget(String year, String month, int budget) {
    if (budget < 0) {
        System.out.println("Budget cannot be negative.");
        return;
    }
    monthlyBudget = budget;
    saveToFile(BUDGET_FILE_PREFIX + year + "_" + month + ".txt", String.valueOf(monthlyBudget));
    System.out.println("Budget saved successfully!");
}
    private void saveToFile(String filename, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(data + "\n");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }
    public int getMonthlyBudget() {
        return monthlyBudget;
    }
}