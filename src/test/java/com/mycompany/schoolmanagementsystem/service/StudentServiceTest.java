package com.mycompany.schoolmanagementsystem.service;

import com.mycompany.schoolmanagementsystem.management.Course;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class StudentServiceTest {

    private final StudentService studentService = new StudentService();

    @Test
    public void testCalculateTotalCredits_shouldReturnCorrectSum() {
        // Arrange
        Course c1 = new Course();
        c1.setCredits(3);
        Course c2 = new Course();
        c2.setCredits(4);

        List<Course> selectedCourses = List.of(c1, c2);

        // Act
        int total = studentService.calculateTotalCredits(selectedCourses);

        // Assert
        assertEquals(7, total);
    }

    @Test
    public void testCalculateTotalCredits_nullList_shouldReturnZero() {
        assertEquals(0, studentService.calculateTotalCredits(null));
    }

    @Test
    public void testIsCourseAlreadyTaken_shouldReturnTrueIfTaken() {
        Course c1 = new Course();
        c1.setCourseID(10);

        List<Course> takenCourses = List.of(c1);

        boolean result = studentService.isCourseAlreadyTaken(10, takenCourses);

        assertTrue(result);
    }

    @Test
    public void isCourseAlreadyTaken_shouldReturnFalseWhenCourseNotTaken() {
        // Arrange
        Course c1 = new Course();
        c1.setCourseID(5); // only ID 5 is in the list
        List<Course> takenCourses = List.of(c1);

        int newCourseID = 10;

        // Act
        boolean result = studentService.isCourseAlreadyTaken(newCourseID, takenCourses);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testIsCreditWithinLimit_shouldReturnTrueIfWithinLimit() {
        Course c1 = new Course();
        c1.setCredits(3);
        List<Course> takenCourses = List.of(c1);

        Course newCourse = new Course();
        newCourse.setCredits(4);

        int maxCredit = 10;

        boolean result = studentService.isCreditWithinLimit(takenCourses, newCourse, maxCredit);

        assertTrue(result);
    }

    @Test
    public void testIsCreditWithinLimit_shouldReturnFalseIfOverLimit() {
        Course c1 = new Course();
        c1.setCredits(6);
        List<Course> takenCourses = List.of(c1);

        Course newCourse = new Course();
        newCourse.setCredits(5);

        int maxCredit = 10;

        boolean result = studentService.isCreditWithinLimit(takenCourses, newCourse, maxCredit);

        assertFalse(result);
    }

    @Test
    public void isMandatoryCoursesCompleted_shouldReturnTrueWhenAllMandatoryCoursesTaken() {
        // Arrange
        Course m1 = new Course();
        m1.setCourseID(1);
        Course m2 = new Course();
        m2.setCourseID(2);
        List<Course> mandatoryCourses = List.of(m1, m2);

        Course t1 = new Course();
        t1.setCourseID(1);
        Course t2 = new Course();
        t2.setCourseID(2);
        List<Course> takenCourses = List.of(t1, t2);

        // Act
        boolean result = studentService.isMandatoryCoursesCompleted(mandatoryCourses, takenCourses);

        // Assert
        assertTrue(result);
    }

    @Test
    public void isMandatoryCoursesCompleted_shouldReturnFalseWhenMissingMandatoryCourse() {
        // Arrange
        Course m1 = new Course();
        m1.setCourseID(1);
        Course m2 = new Course();
        m2.setCourseID(2);
        List<Course> mandatoryCourses = List.of(m1, m2);

        Course t1 = new Course();
        t1.setCourseID(1); // only one taken
        List<Course> takenCourses = List.of(t1);

        // Act
        boolean result = studentService.isMandatoryCoursesCompleted(mandatoryCourses, takenCourses);

        // Assert
        assertFalse(result);
    }

}
