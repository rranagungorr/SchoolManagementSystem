package com.mycompany.schoolmanagementsystem.examsys.DAO;

import com.mycompany.schoolmanagementsystem.management.Student;
import org.junit.jupiter.api.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StudentDAOTest {

    private final StudentDAO studentDAO = new StudentDAO();
    private int createdId;

    @AfterEach
    void cleanup() {
        if (createdId > 0) {
            studentDAO.delete(createdId);
            createdId = 0;
        }
    }

    @Test
    void testCreateAndGetByID() {
        Student s = generateTestStudent("unique_student@test.com", "stu_user1");
        createdId = studentDAO.create(s);

        assertTrue(createdId > 0);
        Student result = studentDAO.getByID(createdId);
        assertNotNull(result);
        assertEquals("John", result.getName());
    }

    @Test
    void testGetAll_shouldContainInsertedStudent() {
        Student s = generateTestStudent("list_student@test.com", "list_user");
        createdId = studentDAO.create(s);

        List<Student> list = studentDAO.getAll();
        assertTrue(list.stream().anyMatch(stu -> stu.getStudentID() == createdId));
    }

    @Test
    void testUpdate_shouldModifyStudent() {
        Student s = generateTestStudent("update_student@test.com", "update_user");
        createdId = studentDAO.create(s);

        Student toUpdate = studentDAO.getByID(createdId);
        toUpdate.setName("Updated");
        toUpdate.setGeneralAverage(3.5);

        boolean success = studentDAO.update(toUpdate);
        assertTrue(success);

        Student updated = studentDAO.getByID(createdId);
        assertEquals("Updated", updated.getName());
        assertEquals(3.5, updated.getGeneralAverage());
    }

    @Test
    void testEmailExists_shouldReturnTrue() {
        Student s = generateTestStudent("exists@test.com", "emailcheck_user");
        createdId = studentDAO.create(s);

        assertTrue(studentDAO.emailExists("exists@test.com"));
    }

    // Yardımcı metot
    private Student generateTestStudent(String email, String username) {
        Student s = new Student();
        s.setName("John");
        s.setSurname("Doe");
        s.setCredits(0);
        s.setClassLevel(1);
        s.setEmail(email);
        s.setDepartmentID(1);
        s.setUsername(username);
        s.setPassword("12345");
        s.setGender("Other");
        s.setSemesterID(1);
        s.setGeneralAverage(2.0);
        return s;
    }
}
