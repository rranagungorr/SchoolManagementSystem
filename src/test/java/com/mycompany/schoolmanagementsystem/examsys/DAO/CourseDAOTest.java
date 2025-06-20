package com.mycompany.schoolmanagementsystem.examsys.DAO;

import com.mycompany.schoolmanagementsystem.management.Course;
import org.junit.jupiter.api.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CourseDAOTest {

    private final CourseDAO courseDAO = new CourseDAO();
    private int createdCourseID;

    @AfterEach
    void cleanup() {
        if (createdCourseID > 0) {
            courseDAO.delete(createdCourseID);
            createdCourseID = 0;
        }
    }

    @Test
    void testCreateAndGetByID() {
        Course c = generateSampleCourse("CS101", "Intro to Java");
        createdCourseID = courseDAO.create(c);

        assertTrue(createdCourseID > 0);
        Course result = courseDAO.getByID(createdCourseID);
        assertNotNull(result);
        assertEquals("CS101", result.getCourseCode());
        assertEquals("Intro to Java", result.getCourseName());
    }

    @Test
    void testUpdate_shouldModifyFields() {
        Course c = generateSampleCourse("CS102", "Old Title");
        createdCourseID = courseDAO.create(c);

        Course toUpdate = courseDAO.getByID(createdCourseID);
        toUpdate.setCourseName("Updated Title");
        boolean updated = courseDAO.update(toUpdate);
        assertTrue(updated);

        Course updatedCourse = courseDAO.getByID(createdCourseID);
        assertEquals("Updated Title", updatedCourse.getCourseName());
    }

    @Test
    void testGetAll_shouldContainNewCourse() {
        Course c = generateSampleCourse("CS103", "All Check");
        createdCourseID = courseDAO.create(c);

        List<Course> all = courseDAO.getAll();
        assertTrue(all.stream().anyMatch(course -> course.getCourseID() == createdCourseID));
    }

    @Test
    void testDelete_shouldRemoveCourse() {
        Course c = generateSampleCourse("CS104", "To Be Deleted");
        int idToDelete = courseDAO.create(c);

        boolean deleted = courseDAO.delete(idToDelete);
        assertTrue(deleted);

        Course deletedCourse = courseDAO.getByID(idToDelete);
        assertNull(deletedCourse);
    }

    private Course generateSampleCourse(String code, String name) {
        Course c = new Course();
        c.setCourseName(name);
        c.setCourseCode(code);
        c.setCredits(3);
        c.setDepartmentID(1);
        c.setInstructorID(null); // nullable
        c.setClassroomID(1);
        c.setSemesterID(1);
        c.setStartTime("10:00");
        c.setEndTime("12:00");
        c.setWeekDay("Monday");
        c.setHours(2);
        c.setClassLevel(1);
        return c;
    }
}
