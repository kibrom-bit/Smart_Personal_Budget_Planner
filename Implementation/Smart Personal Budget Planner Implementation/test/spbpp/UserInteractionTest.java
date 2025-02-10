package spbpp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class UserInteractionTest {
    private UserInteraction userInteraction;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUp() {
        userInteraction = new UserInteraction();
        System.setOut(new PrintStream(outputStreamCaptor)); // Redirect output to capture it
    }

    @After
    public void tearDown() {
        System.setOut(originalOut); // Restore original System.out
    }

    /**
     * Test of getUser Choice method, of class UserInteraction.
     */
    @Test
    public void testGetUserChoice() {
        System.out.println("getUser Choice");
        String simulatedInput = "5\n"; // Simulate user input
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        int result = userInteraction.getUserChoice("Enter your choice: ");
        assertEquals(5, result); // Expect the result to be 5
    }

    /**
     * Test of getUser Input method, of class UserInteraction.
     */
    @Test
    public void testGetUserInput() {
        System.out.println("getUser Input");
        String simulatedInput = "Test Input\n"; // Simulate user input
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        String result = userInteraction.getUserInput("Enter something: ");
        assertEquals("Test Input", result); // Expect the result to match the input
    }

    /**
     * Test of print method, of class UserInteraction.
     */
    @Test
    public void testPrint() {
        System.out.println("print");
        String message = "Hello, World!";
        userInteraction.print(message);

        // Check if the output matches the expected message
        assertEquals("Hello, World!", outputStreamCaptor.toString().trim());
    }

    /**
     * Test of close method, of class UserInteraction.
     */
    @Test
    public void testClose() {
        System.out.println("close");
        userInteraction.close();
           }
}