package spbpp;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class SavingsManager {
    private static final String SAVINGS_FILE_PREFIX = "savings_";
    private Map<String, Integer> savingsGoals = new HashMap<>(); // Store goals by year and month

    public int loadSavingsGoal(String year, String month) {
        String savingsFile = SAVINGS_FILE_PREFIX + year + "_" + month + ".txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(savingsFile))) {
            int goal = Integer.parseInt(reader.readLine());
            savingsGoals.put(year + "_" + month, goal); // Store the loaded goal
            System.out.println("Savings goal loaded: $" + goal);
            return goal; // Return the loaded goal
        } catch (IOException e) {
            System.out.println("Error loading savings goal: " + e.getMessage());
            return 0; // Return 0 if loading fails
        }
    }
    public int getSavingsGoal(String year, String month) {
    return savingsGoals.getOrDefault(year + "_" + month, 0);
    }
    public void setSavingsGoal(String year, String month, int goal) {
        savingsGoals.put(year + "_" + month, goal); // Store the goal in the map
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

    // Optional: Method to get the current savings goal for a specific year and month
 
}