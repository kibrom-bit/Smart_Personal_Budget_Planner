package spbpp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.*;

public class MainTest {
    private InputStream originalSystemIn;
    private Main main;
    private UserManager userManager;

    @Before
    public void setUp() {
        originalSystemIn = System.in; // Save the original System.in
        userManager = new UserManager();
        main = new Main(userManager, new UserInteraction()); // Create an instance of Main
    }

    @After
    public void tearDown() {
        System.setIn(originalSystemIn); // Restore original System.in
    }

    @Test
    public void testLogin_Success() {
        // Simulate user input for a successful login
        String simulatedInput = "testUser \n" + "testPass\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        // Create a test user
        userManager.createAccount("testUser ", "testPass");

        boolean result = main.login();
        assertTrue(result);
    }

    @Test
    public void testLogin_Failure() {
        // Simulate user input for a failed login
        String simulatedInput = "wrongUser \n" + "wrongPass\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        boolean result = main.login();
        assertFalse(result);
    }
@Test
public void testCreateAccount() {
    // Simulate user input for account creation
    String simulatedInput = "newUser  \n" + "newPass\n"; // Ensure this matches the expected prompts
    System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

    main.createAccount(); // This should prompt for username and password

    // Check if the account was created successfully
    assertTrue(userManager.authenticate("newUser  ", "newPass"));
}

    @Test
    public void testSelectYearMonth() {
        // Simulate user input for selecting year and month
        String simulatedInput = "2024\n" + "January\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        // Create a test user to log in
        userManager.createAccount("testUser ", "testPass");
        main.login(); // Log in the test user

        main.selectYearMonth();

        // Check if the budget and savings goal were loaded
        assertEquals(0, main.budgetManager.getMonthlyBudget()); // Assuming no budget is set
        assertEquals(0, main.savingsManager.getSavingsGoal("2024", "january")); // Assuming no savings goal is set
    }

    @Test
    public void testAddExpense() {
        // Simulate user input for adding an expense
        String simulatedInput = "100\n" + "Food\n" + "2024-01-01\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        // Create a test user to log in
        userManager.createAccount("testUser ", "testPass");
        main.login(); // Log in the test user

        main.setBudget(); // Set a budget first
        main.addExpense();

        // Check if the expense was added
        assertEquals(100, main.expenseManager.getTotalExpenses("2024", "january"));
    }

    @Test
    public void testEditExpense() {
        // Simulate user input for adding an expense first
        String addExpenseInput = "100\n" + "Food\n" + "2024-01-01\n";
        System.setIn(new ByteArrayInputStream(addExpenseInput.getBytes()));

        // Create a test user to log in
        userManager.createAccount("testUser ", "testPass");
        main.login(); // Log in the test user

        main.setBudget(); // Set a budget first
        main.addExpense(); // Add an expense

        // Now simulate user input for editing the expense
        String editExpenseInput = "1\n" + "150\n" + "Groceries\n" + "2024-01-02\n";
        System.setIn(new ByteArrayInputStream(editExpenseInput.getBytes()));

        main.editExpense();

        // Check if the expense was edited
        assertEquals(150, main.expenseManager.getTotalExpenses("2024", "january"));
    }

    @Test
    public void testViewExpenses() {
        // Simulate user input for adding an expense first
        String addExpenseInput = "100\n" + "Food\n" + "2024-01-01\n";
        System.setIn(new ByteArrayInputStream(addExpenseInput.getBytes()));

        // Create a test user to log in
        userManager.createAccount("testUser ", "testPass");
        main.login(); // Log in the test user

        main.setBudget(); // Set a budget first
        main.addExpense(); // Add an expense

        // Now view expenses
        main.viewExpenses();

        // You can check the console output manually or redirect System.out to capture it
        // This part is more complex and may require additional setup
    }

    @Test
    public void testSetBudget() {
        // Simulate user input for setting a budget
        String simulatedInput = "500\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        // Create a test user to log in
        userManager.createAccount("testUser ", "testPass");
        main.login(); // Log in the test user

        main.setBudget();

        // Check if the budget was set
        assertEquals(500, main.budgetManager.getMonthlyBudget());
    }

    @Test
    public void testSetSavingsGoal() {
        // Simulate user input for setting a savings goal
        String simulatedInput = "2000\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        // Create a test user to log in
        userManager.createAccount("testUser ", "testPass");
        main.login(); // Log in the test user

        main.setSavingsGoal();

        // Check if the savings goal was set
        assertEquals(2000, main.savingsManager.getSavingsGoal("2024", "january"));
    }
}