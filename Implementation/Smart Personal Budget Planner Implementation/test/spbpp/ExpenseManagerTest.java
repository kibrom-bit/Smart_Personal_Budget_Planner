package spbpp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class ExpenseManagerTest {
    private ExpenseManager expenseManager;
    private static final String TEST_YEAR = "2024";
    private static final String TEST_MONTH = "january";
    private static final String TEST_EXPENSE_FILE = "expenses_" + TEST_YEAR + "_" + TEST_MONTH + ".txt";

    @Before
    public void setUp() {
        expenseManager = new ExpenseManager();
    }

    @After
    public void tearDown() {
        // Clean up the test expenses file after each test
        File file = new File(TEST_EXPENSE_FILE);
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * Test of addExpense method, of class ExpenseManager.
     */
    @Test
    public void testAddExpense() {
        System.out.println("addExpense");
        String expenseData = "2024-01-01,100,Food"; // Example expense data
        expenseManager.addExpense(TEST_YEAR, TEST_MONTH, expenseData);

        // Load expenses to verify that the expense was added
        List<String> expenses = expenseManager.loadExpenses(TEST_YEAR, TEST_MONTH);
        assertEquals(1, expenses.size()); // Expect one expense to be added
        assertEquals(expenseData, expenses.get(0)); // Check if the added expense matches
    }

    /**
     * Test of editExpense method, of class ExpenseManager.
     */
    @Test
    public void testEditExpense() {
        System.out.println("editExpense");
        String expenseData = "2024-01-01,100,Food"; // Example expense data
        expenseManager.addExpense(TEST_YEAR, TEST_MONTH, expenseData);

        // Load expenses to edit
        List<String> expenses = expenseManager.loadExpenses(TEST_YEAR, TEST_MONTH);
        String newExpenseData = "2024-01-01,150,Groceries"; // New expense data
        expenseManager.editExpense(TEST_YEAR, TEST_MONTH, expenses, 0, newExpenseData);

        // Load expenses again to verify the edit
        List<String> updatedExpenses = expenseManager.loadExpenses(TEST_YEAR, TEST_MONTH);
        assertEquals(1, updatedExpenses.size()); // Still expect one expense
        assertEquals(newExpenseData, updatedExpenses.get(0)); // Check if the expense was updated
    }

    /**
     * Test of loadExpenses method, of class ExpenseManager.
     */
    @Test
    public void testLoadExpenses() {
        System.out.println("loadExpenses");
        String expenseData = "2024-01-01,100,Food"; // Example expense data
        expenseManager.addExpense(TEST_YEAR, TEST_MONTH, expenseData);

        // Load expenses
        List<String> expenses = expenseManager.loadExpenses(TEST_YEAR, TEST_MONTH);
        assertEquals(1, expenses.size()); // Expect one expense to be loaded
        assertEquals(expenseData, expenses.get(0)); // Check if the loaded expense matches
    }

    /**
     * Test of calculateTotalExpenses method, of class ExpenseManager.
     */
    @Test
    public void testCalculateTotalExpenses() {
        System.out.println("calculateTotalExpenses");
        String expenseData1 = "2024-01-01,100,Food"; // Example expense data
        String expenseData2 = "2024-01-02,200,Transport"; // Another expense
        expenseManager.addExpense(TEST_YEAR, TEST_MONTH, expenseData1);
        expenseManager.addExpense(TEST_YEAR, TEST_MONTH, expenseData2);

        // Calculate total expenses
        int total = expenseManager.calculateTotalExpenses(TEST_YEAR, TEST_MONTH);
        assertEquals(300, total); // Expect total to be 300
    }

    /**
     * Test of getTotalExpenses method, of class ExpenseManager.
     */
    @Test
    public void testGetTotalExpenses() {
        System.out.println("getTotalExpenses");
        String expenseData = "2024-01-01,100,Food"; // Example expense data
        expenseManager.addExpense(TEST_YEAR, TEST_MONTH, expenseData);

        // Get total expenses
        int total = expenseManager.getTotalExpenses(TEST_YEAR, TEST_MONTH);
        assertEquals(100, total); // Expect total to be 100
    }
}