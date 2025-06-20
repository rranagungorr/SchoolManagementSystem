package com.mycompany.schoolmanagementsystem.service;

import java.sql.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class AdminServiceTest {

    private final AdminService adminService = new AdminService();

    @Test
    public void testValidateDateRange_withinRange_shouldReturnTrue() {
        Date selectedDate = Date.valueOf("2024-12-01");
        String startDateStr = "2024-11-04";
        String endDateStr = "2025-01-03";

        boolean result = adminService.validateDateRange(selectedDate, startDateStr, endDateStr);

        assertTrue(result);
    }

    @Test
    public void testValidateDateRange_beforeRange_shouldReturnFalse() {
        Date selectedDate = Date.valueOf("2024-10-31");
        String startDateStr = "2024-11-04";
        String endDateStr = "2025-01-03";

        boolean result = adminService.validateDateRange(selectedDate, startDateStr, endDateStr);

        assertFalse(result);
    }

    @Test
    public void testValidateDateRange_afterRange_shouldReturnFalse() {
        Date selectedDate = Date.valueOf("2025-01-05");
        String startDateStr = "2024-11-04";
        String endDateStr = "2025-01-03";

        boolean result = adminService.validateDateRange(selectedDate, startDateStr, endDateStr);

        assertFalse(result);
    }

    @Test
    public void testValidateDateRange_onStartDate_shouldReturnTrue() {
        Date selectedDate = Date.valueOf("2024-11-04");
        String startDateStr = "2024-11-04";
        String endDateStr = "2025-01-03";

        boolean result = adminService.validateDateRange(selectedDate, startDateStr, endDateStr);

        assertTrue(result);
    }

    @Test
    public void testValidateDateRange_onEndDate_shouldReturnTrue() {
        Date selectedDate = Date.valueOf("2025-01-03");
        String startDateStr = "2024-11-04";
        String endDateStr = "2025-01-03";

        boolean result = adminService.validateDateRange(selectedDate, startDateStr, endDateStr);

        assertTrue(result);
    }

    @ParameterizedTest
    @CsvSource({
        // valid midterm date
        "2024-11-15, Midterm, true",
        // invalid midterm date (before range)
        "2024-10-30, Midterm, false",
        // valid final date
        "2025-01-20, Final, true",
        // invalid final date (after range)
        "2025-01-30, Final, false",
        // invalid examType
        "2024-11-15, Quiz, false"
    })
    public void testValidateExamDate(String dateStr, String examType, boolean expected) {
        Date selectedDate = Date.valueOf(dateStr);
        boolean result = adminService.validateExamDate(selectedDate, examType);
        assertEquals(expected, result);
    }
}
