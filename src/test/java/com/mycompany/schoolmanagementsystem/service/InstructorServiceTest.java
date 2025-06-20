/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.schoolmanagementsystem.service;

import com.mycompany.schoolmanagementsystem.examsys.DAO.ExamResultDAO;
import com.mycompany.schoolmanagementsystem.examsys.DAO.StudentExamDAO;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author Merve
 */
public class InstructorServiceTest {
    
    private final InstructorService instructorService = new InstructorService();

    @Test
    public void testUpdateAttendanceStatus_shouldUpdateAllSelectedDates() {
        Map<LocalDate, String> map = new HashMap<>();
        List<LocalDate> selectedDates = List.of(
                LocalDate.of(2025, 6, 18),
                LocalDate.of(2025, 6, 19)
        );

        instructorService.updateAttendanceStatus(map, selectedDates, "Attend");

        assertEquals("Attend", map.get(LocalDate.of(2025, 6, 18)));
        assertEquals("Attend", map.get(LocalDate.of(2025, 6, 19)));
        assertEquals(2, map.size());
    }

    @Test
    public void testUpdateAttendanceStatus_shouldDoNothingIfListEmpty() {
        Map<LocalDate, String> map = new HashMap<>();
        List<LocalDate> selectedDates = Collections.emptyList();

        instructorService.updateAttendanceStatus(map, selectedDates, "Not Attend");

        assertTrue(map.isEmpty());
    }

    @Test
    public void testUpdateAttendanceStatus_shouldOverrideExistingStatus() {
        LocalDate date = LocalDate.of(2025, 6, 18);
        Map<LocalDate, String> map = new HashMap<>(Map.of(date, "Attend"));
        List<LocalDate> selectedDates = List.of(date);

        instructorService.updateAttendanceStatus(map, selectedDates, "Not Attend");

        assertEquals("Not Attend", map.get(date));
    }
    
   
    @ParameterizedTest
    @CsvSource({
        "5, 101, 90, 555",   // studentID, courseID, score, examID
        "10, 200, 75, 777",
        "7, 150, 100, 888"
    })
    public void testAssignScoreToStudent_shouldSucceed(int studentId, int courseId, int score, int examId) {
        StudentExamDAO mockExamDAO = mock(StudentExamDAO.class);
        ExamResultDAO mockResultDAO = mock(ExamResultDAO.class);
        InstructorService service = new InstructorService(mockExamDAO, mockResultDAO);

        when(mockExamDAO.getExamIDByCourseAndStudent(courseId, studentId)).thenReturn(examId);
        when(mockResultDAO.assignScore(studentId, examId, score)).thenReturn(true);

        boolean result = service.assignScoreToStudent(studentId, courseId, score);

        assertTrue(result);
        verify(mockExamDAO).getExamIDByCourseAndStudent(courseId, studentId);
        verify(mockResultDAO).assignScore(studentId, examId, score);
    }
}


