package spbpp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.Assert.*;

public class BudgetManagerTest {
    private BudgetManager budgetManager;
    private static final String TEST_YEAR = "2024";
    private static final String TEST_MONTH = "january";
    private static final String TEST_BUDGET_FILE = "budget_" + TEST_YEAR + "_" + TEST_MONTH + ".txt";

    @Before
    public void setUp() {
        budgetManager = new BudgetManager();
    }

    @After
    public void tearDown() {
        // Clean up the test budget file after each test
        File file = new File(TEST_BUDGET_FILE);
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * Test of loadBudget method, of class BudgetManager.
     */
    @Test
    public void testLoadBudget() {
        System.out.println("loadBudget");

        // Create a test budget file with a budget
        try (FileWriter writer = new FileWriter(TEST_BUDGET_FILE)) {
            writer.write("1000\n"); // Write a budget of $1000
        } catch (IOException e) {
            fail("Failed to create test budget file: " + e.getMessage());
        }

        // Load the budget
        budgetManager.loadBudget(TEST_YEAR, TEST_MONTH);
        assertEquals(1000, budgetManager.getMonthlyBudget()); // Expect the loaded budget to be 1000
    }

    /**
     * Test of setBudget method, of class BudgetManager.
     */
    @Test
    public void testSetBudget() {
        System.out.println("setBudget");

        // Set a budget
        budgetManager.setBudget(TEST_YEAR, TEST_MONTH, 1500);

        // Verify that the budget was set correctly
        assertEquals(1500, budgetManager.getMonthlyBudget()); // Expect the budget to be 1500
    }

    /**
     * Test of getMonthlyBudget method, of class BudgetManager.
     */
    @Test
    public void testGetMonthlyBudget() {
        System.out.println("getMonthlyBudget");

        // Initially, the budget should be 0
        assertEquals(0, budgetManager.getMonthlyBudget());

        // Set a budget and check again
        budgetManager.setBudget(TEST_YEAR, TEST_MONTH, 2000);
        assertEquals(2000, budgetManager.getMonthlyBudget()); // Expect the budget to be 2000
    }
}