package spbpp;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DateUtilsTest {

    public DateUtilsTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of parseDate method, of class DateUtils.
     */
    @Test
    public void testParseDate_ValidFormats() {
        System.out.println("parseDate - Valid Formats");

        // Test various valid date formats
        assertEquals(LocalDate.of(2024, 1, 1), DateUtils.parseDate("2024-01-01"));
        assertEquals(LocalDate.of(2024, 1, 1), DateUtils.parseDate("01/01/2024"));
        assertEquals(LocalDate.of(2024, 1, 1), DateUtils.parseDate("01-01-2024"));
        assertEquals(LocalDate.of(2024, 1, 1), DateUtils.parseDate("2024/01/01"));
        assertEquals(LocalDate.of(2024, 1, 1), DateUtils.parseDate("01-01-2024"));
    }

    @Test(expected = DateTimeParseException.class)
    public void testParseDate_InvalidFormat() {
        System.out.println("parseDate - Invalid Format");
        // Test an invalid date format
        DateUtils.parseDate("invalid-date");
    }
}