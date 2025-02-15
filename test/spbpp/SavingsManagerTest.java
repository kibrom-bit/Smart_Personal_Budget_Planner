package spbpp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.OptionalInt;

public class SavingsManagerTest {

    private SavingsManager savingsManager;
    private final String testYear = "2024";
    private final String testMonth = "january";
    private final String testFile = "savings_" + testYear + "_" + testMonth + ".txt";

    @Before
    public void setUp() {
        savingsManager = new SavingsManager();
        try {
            Files.deleteIfExists(Paths.get(testFile));
        } catch (IOException e) {

        }
    }

    @After
    public void tearDown() {
        try {
            Files.deleteIfExists(Paths.get(testFile));
        } catch (IOException e) {
            // Ignore if the file doesn't exist
        }
    }

    @Test
    public void testLoadSavingsGoal_FileExists() throws IOException {
        double expectedGoal = 500.0;
        Files.write(Paths.get(testFile), String.valueOf(expectedGoal).getBytes());

        OptionalInt result = savingsManager.loadSavingsGoal(testYear, testMonth);
        assertTrue(result.isPresent());
        assertEquals((int) expectedGoal, result.getAsInt());
    }

    @Test
    public void testLoadSavingsGoal_FileNotExists() {
        OptionalInt result = savingsManager.loadSavingsGoal(testYear, testMonth);
        assertFalse(result.isPresent());
    }

    @Test
    public void testGetSavingsGoal_GoalSet() {
        double expectedGoal = 750.0;
        savingsManager.setSavingsGoal(testYear, testMonth, expectedGoal);
        double result = savingsManager.getSavingsGoal(testYear, testMonth);
        assertEquals(expectedGoal, result, 0.0);
    }

    @Test
    public void testGetSavingsGoal_GoalNotSet() {
        double result = savingsManager.getSavingsGoal(testYear, testMonth);
        assertEquals(0.0, result, 0.0);
    }

    @Test
    public void testSetSavingsGoal() throws IOException {
        double goal = 1000.0;
        savingsManager.setSavingsGoal(testYear, testMonth, goal);
        try (BufferedReader reader = new BufferedReader(new FileReader(testFile))) {
            double fileGoal = Double.parseDouble(reader.readLine());
            assertEquals(goal, fileGoal, 0.0);
        }
    }
}