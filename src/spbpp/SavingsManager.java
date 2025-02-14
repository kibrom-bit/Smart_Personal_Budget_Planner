package spbpp;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.OptionalInt;

public class SavingsManager {
    private static final String SAVINGS_FILE_PREFIX = "savings_";
    private Map<String, Double> savingsGoals = new HashMap<>(); // Store goals as doubles

    public OptionalInt loadSavingsGoal(String year, String month) {
        String savingsFile = SAVINGS_FILE_PREFIX + year + "_" + month + ".txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(savingsFile))) {
            double goal = Double.parseDouble(reader.readLine()); // Parse as double
            savingsGoals.put(year + "_" + month, goal);
            System.out.println("Savings goal loaded: $" + goal);
            return OptionalInt.of((int) goal); // Return the goal wrapped in an OptionalInt
        } catch (IOException e) {
            System.out.println("Error loading savings goal: " + e.getMessage());
            return OptionalInt.empty();
        }
    }

    public double getSavingsGoal(String year, String month) {
        return savingsGoals.getOrDefault(year + "_" + month, 0.0); // Return 0.0
    }

    public void setSavingsGoal(String year, String month, double goal) {
        savingsGoals.put(year + "_" + month, goal);
        saveToFile(SAVINGS_FILE_PREFIX + year + "_" + month + ".txt", String.valueOf(goal));
        System.out.println("Savings goal saved successfully!");
    }

    private void saveToFile(String filename, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(data + "\n");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }
}