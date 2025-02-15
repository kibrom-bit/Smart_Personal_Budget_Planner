package spbpp;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import org.junit.Test;
import static org.junit.Assert.*;

public class DateUtilsTest {

    @Test
    public void testParseDate_ValidFormats() {
        System.out.println("parseDate - Valid Formats");

        // Test various valid date formats
        assertEquals(LocalDate.of(2024, 1, 1), DateUtils.parseDate("2024-01-01"));
        assertEquals(LocalDate.of(2024, 1, 1), DateUtils.parseDate("01/01/2024"));
        assertEquals(LocalDate.of(2024, 1, 1), DateUtils.parseDate("01-01-2024"));
        assertEquals(LocalDate.of(2024, 1, 1), DateUtils.parseDate("2024/01/01"));
        assertEquals(LocalDate.of(2024, 1, 1), DateUtils.parseDate("01-01-2024"));
        //Test other date combinations
        assertEquals(LocalDate.of(2023, 12, 25), DateUtils.parseDate("2023-12-25"));
        assertEquals(LocalDate.of(2025, 6, 15), DateUtils.parseDate("15/06/2025"));
    }

    @Test(expected = DateTimeParseException.class)
    public void testParseDate_InvalidFormat() {
        System.out.println("parseDate - Invalid Format");
        // Test an invalid date format
        DateUtils.parseDate("invalid-date");
    }

    @Test(expected = DateTimeParseException.class)
    public void testParseDate_EmptyInput() {
        System.out.println("parseDate - Empty Input");
        // Test empty input
        DateUtils.parseDate("");
    }


}