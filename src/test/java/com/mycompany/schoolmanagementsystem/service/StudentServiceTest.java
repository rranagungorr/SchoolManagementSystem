
package com.mycompany.schoolmanagementsystem.service;

import com.mycompany.schoolmanagementsystem.management.Course;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;


public class StudentServiceTest {
     private final StudentService studentService = new StudentService();
      
        @Test
    public void testJUnitSetup() {
        System.out.println("✅ JUnit works!");
    }
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
}

