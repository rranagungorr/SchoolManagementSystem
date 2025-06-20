package com.mycompany.schoolmanagementsystem.examsys.DAO;

import com.mycompany.schoolmanagementsystem.management.Admin;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AdminDAOTest {

    private final AdminDAO adminDAO = new AdminDAO();
    private int createdAdminId;

    @AfterEach
    public void cleanup() {
        if (createdAdminId > 0) {
            adminDAO.delete(createdAdminId);
            createdAdminId = 0;
        }
    }

    @Test
    public void testCreateAndGetByID() {
        Admin admin = new Admin();
        admin.setName("Test");
        admin.setSurname("Admin");
        admin.setEmail("test.admin@example.com");
        admin.setGender("Other");
        admin.setUsername("test_admin");
        admin.setPassword("secure123");

        createdAdminId = adminDAO.create(admin);
        assertTrue(createdAdminId > 0, "Admin ID should be greater than 0");

        Admin retrieved = adminDAO.getByID(createdAdminId);
        assertNotNull(retrieved);
        assertEquals("Test", retrieved.getName());
        assertEquals("Admin", retrieved.getSurname());
    }

    @Test
    public void testGetAll_shouldIncludeInsertedAdmin() {
        Admin admin = new Admin();
        admin.setName("List");
        admin.setSurname("Admin");
        admin.setEmail("list.admin@example.com");
        admin.setGender("Female");
        admin.setUsername("list_admin");
        admin.setPassword("pass123");
        createdAdminId = adminDAO.create(admin);

        List<Admin> admins = adminDAO.getAll();
        assertTrue(admins.stream().anyMatch(a -> a.getAdminID() == createdAdminId));
    }

    @Test
    public void testUpdate_shouldModifyFields() {
        Admin admin = new Admin();
        admin.setName("Old");
        admin.setSurname("Name");
        admin.setEmail("old.name@example.com");
        admin.setGender("Male");
        admin.setUsername("old_name");
        admin.setPassword("pass");
        createdAdminId = adminDAO.create(admin);

        Admin toUpdate = adminDAO.getByID(createdAdminId);
        toUpdate.setName("Updated");
        toUpdate.setEmail("updated@example.com");

        boolean updated = adminDAO.update(toUpdate);
        assertTrue(updated);

        Admin updatedAdmin = adminDAO.getByID(createdAdminId);
        assertEquals("Updated", updatedAdmin.getName());
        assertEquals("updated@example.com", updatedAdmin.getEmail());
    }

    @Test
    public void testDelete_shouldRemoveAdmin() {
        Admin admin = new Admin();
        admin.setName("ToDelete");
        admin.setSurname("Admin");
        admin.setEmail("delete.admin@example.com");
        admin.setGender("Other");
        admin.setUsername("del_admin");
        admin.setPassword("pass123");
        int idToDelete = adminDAO.create(admin);

        boolean deleted = adminDAO.delete(idToDelete);
        assertTrue(deleted);

        Admin result = adminDAO.getByID(idToDelete);
        assertNull(result);
    }
}
