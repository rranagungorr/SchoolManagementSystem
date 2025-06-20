package com.mycompany.schoolmanagementsystem.examsys.DAO;

import com.mycompany.schoolmanagementsystem.examsys.ExamResult;
import org.junit.jupiter.api.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ExamResultDAOTest {

    private final ExamResultDAO examResultDAO = new ExamResultDAO();
    private int createdId;

    @AfterEach
    void cleanup() {
        if (createdId > 0) {
            examResultDAO.delete(createdId);
            createdId = 0;
        }
    }

    @Test
    void testCreateAndGetByID() {
        ExamResult result = new ExamResult();
        result.setStudentExamID(10); // Test veritabanında var olan bir ID olmalı
        result.setScore(85.5);
        createdId = examResultDAO.create(result);

        assertTrue(createdId > 0);
        ExamResult fromDb = examResultDAO.getByID(createdId);
        assertNotNull(fromDb);
        assertEquals(85.5, fromDb.getScore());
    }

    @Test
    void testGetAll_containsCreatedResult() {
        ExamResult result = new ExamResult();
        result.setStudentExamID(10);
        result.setScore(70.0);
        createdId = examResultDAO.create(result);

        List<ExamResult> list = examResultDAO.getAll();
        assertTrue(list.stream().anyMatch(er -> er.getExamResultID() == createdId));
    }

    @Test
    void testUpdate_shouldChangeScore() {
        ExamResult result = new ExamResult();
        result.setStudentExamID(10);
        result.setScore(60.0);
        createdId = examResultDAO.create(result);

        result.setExamResultID(createdId);
        result.setScore(95.0);
        boolean success = examResultDAO.update(result);
        assertTrue(success);

        ExamResult updated = examResultDAO.getByID(createdId);
        assertEquals(95.0, updated.getScore());
    }

    @Test
    void testDelete_shouldRemoveRecord() {
        ExamResult result = new ExamResult();
        result.setStudentExamID(10);
        result.setScore(50.0);
        int idToDelete = examResultDAO.create(result);

        boolean deleted = examResultDAO.delete(idToDelete);
        assertTrue(deleted);

        ExamResult deletedResult = examResultDAO.getByID(idToDelete);
        assertNull(deletedResult);
    }

    @Test
    void testGetResultsByStudent() {
        // Öğrenci 1'e ait en az bir not olduğundan emin olun
        List<ExamResult> results = examResultDAO.getResultsByStudent(1);
        assertNotNull(results);
    }

    @Test
    void testIsScoreAlreadyAssigned_shouldReturnFalseInitially() {
        boolean exists = examResultDAO.isScoreAlreadyAssigned(999, 999); // Olmayan bir öğrenci ve sınav
        assertFalse(exists);
    }

    @Test
    void testAssignScore_shouldInsertScore() {
        boolean inserted = examResultDAO.assignScore(21, 16, 88); // Öğrenci 1 ve sınav 1 varsa
        assertTrue(inserted);
    }
}
