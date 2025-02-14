package spbpp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class UserInteractionTest {
    private UserInteraction userInteraction;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private BufferedReader reader;

    @Before
    public void setUp() {
        userInteraction = new UserInteraction();
        System.setOut(new PrintStream(outputStreamCaptor));
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @After
    public void tearDown() {
        System.setOut(originalOut);
        reader = null; // Important: Reset reader after each test
    }


    @Test
    public void testPrint() {
        String message = "Hello, World!";
        userInteraction.print(message);
        System.out.flush();

        // Use a Hamcrest matcher for more flexible assertion:
        assertThat(outputStreamCaptor.toString(), containsString(message));

        // Or, if you prefer JUnit's assertions:
         assertTrue(outputStreamCaptor.toString().contains(message));
    }

    @Test
    public void testClose() {
        userInteraction.close();
        // No specific assertion needed, just ensure it runs without exceptions
    }
}