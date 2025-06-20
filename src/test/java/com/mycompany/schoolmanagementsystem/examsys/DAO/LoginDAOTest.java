package com.mycompany.schoolmanagementsystem.examsys.DAO;

import com.mycompany.schoolmanagementsystem.management.Admin;
import com.mycompany.schoolmanagementsystem.management.Instructor;
import com.mycompany.schoolmanagementsystem.management.Student;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LoginDAOTest {

    private final LoginDAO loginDAO = new LoginDAO();

    @Test
    void testLoginAdmin_validCredentials_shouldReturnAdmin() {
        // Varsayım: veritabanında bu kullanıcı mevcut
        Admin admin = loginDAO.loginAdmin("adminuser", "1");
        assertNotNull(admin, "Valid admin credentials should return an Admin object");
        assertEquals("adminuser", admin.getUsername());
    }

    @Test
    void testLoginAdmin_invalidCredentials_shouldReturnNull() {
        Admin admin = loginDAO.loginAdmin("invalidUser", "wrongPass");
        assertNull(admin, "Invalid admin credentials should return null");
    }

    @Test
    void testLoginInstructor_validCredentials_shouldReturnInstructor() {
        // Varsayım: instructor1 kullanıcısı varsa
        Instructor instructor = loginDAO.loginInstructor("bernakir", "123");
        assertNotNull(instructor);
        assertEquals("bernakir", instructor.getUsername());
    }

    @Test
    void testLoginInstructor_invalidCredentials_shouldReturnNull() {
        Instructor instructor = loginDAO.loginInstructor("wrongUser", "wrongPass");
        assertNull(instructor);
    }

    @Test
    void testLoginStudent_validCredentials_shouldReturnStudent() {
        // Varsayım: student1 kullanıcısı varsa
        Student student = loginDAO.loginStudent("merve.demir", "222");
        assertNotNull(student);
        assertEquals("merve.demir", student.getUsername());
    }

    @Test
    void testLoginStudent_invalidCredentials_shouldReturnNull() {
        Student student = loginDAO.loginStudent("x", "y");
        assertNull(student);
    }
}