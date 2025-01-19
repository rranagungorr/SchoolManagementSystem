/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.schoolmanagementsystem.homepageUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Merve
 */
public class ScheduleManagement {
     private JTable table;
    private JComboBox<String> courseComboBox;
    private String[] courses = {"Math", "Physics", "Computer Science", "History", "Biology"};
    private int selectedRow = -1;
    private int selectedCol = -1;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ScheduleManagement().createAndShowGUI());
    }

    public JPanel createAndShowGUI() {
        JFrame frame = new JFrame("Course Schedule Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 700);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(245, 245, 245, 200)); // Very light cream with transparency

        JLabel titleLabel = new JLabel("Course Schedule", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(102, 0, 102)); // Dark purple
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Column and row names for the schedule
        String[] columnNames = {"Time", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        String[][] data = {
                {"09:00 - 09:50", "", "", "", "", ""},
                {"10:00 - 10:50", "", "", "", "", ""},
                {"11:00 - 11:50", "", "", "", "", ""},
                {"12:00 - 12:50", "", "", "", "", ""},
                {"13:00 - 13:50", "", "", "", "", ""},
                {"14:00 - 14:50", "", "", "", "", ""},
                {"15:00 - 15:50", "", "", "", "", ""},
                {"16:00 - 16:50", "", "", "", "", ""},
                {"17:00 - 17:50", "", "", "", "", ""}
        };

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        table = new JTable(model) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Disable direct cell editing
            }

            @Override
            public Component prepareRenderer(javax.swing.table.TableCellRenderer renderer, int row, int col) {
                Component c = super.prepareRenderer(renderer, row, col);
                if (col > 0 && !getValueAt(row, col).toString().isEmpty()) {
                    c.setBackground(new Color(230, 230, 250)); // Light purple background for filled cells
                } else {
                    c.setBackground(Color.WHITE);
                }
                c.setFont(new Font("Arial", Font.PLAIN, 12));
                return c;
            }
        };
        table.setRowHeight(50);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setGridColor(new Color(102, 0, 102)); // Dark purple grid
        table.setShowGrid(true);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(102, 0, 102)); // Dark purple header background
        table.getTableHeader().setForeground(Color.WHITE);
        table.setSelectionBackground(new Color(153, 50, 204)); // Medium purple for selection
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Add mouse listener for cell selection
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectedRow = table.rowAtPoint(e.getPoint());
                selectedCol = table.columnAtPoint(e.getPoint());

                if (selectedCol > 0) { // Skip the "Time" column
                    table.repaint(); // Repaint to ensure selection styling updates
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Add JComboBox for course selection
        courseComboBox = new JComboBox<>(courses);
        JButton addButton = new JButton("Add Course");
        addButton.addActionListener(e -> {
            if (selectedRow == -1 || selectedCol == -1) {
                JOptionPane.showMessageDialog(frame, "Please select a cell first.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String selectedCourse = (String) courseComboBox.getSelectedItem();
            int courseHours = 3; // Example: All courses have 3 hours
            String classroom = " H214"; // Example classroom

            // Check if enough cells are available for the course
            int availableCells = 0;
            for (int i = selectedRow; i < Math.min(selectedRow + courseHours, table.getRowCount()); i++) {
                String value = (String) table.getValueAt(i, selectedCol);
                if (value == null || value.isEmpty()) {
                    availableCells++;
                } else {
                    break;
                }
            }

            if (availableCells < courseHours) {
                JOptionPane.showMessageDialog(frame, "Not enough consecutive cells for this course.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Add course and classroom to cells
            for (int i = selectedRow; i < selectedRow + courseHours; i++) {
                table.setValueAt(selectedCourse + "\n" + classroom, i, selectedCol);
            }
        });

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        controlPanel.add(new JLabel("Select Course:"));
        controlPanel.add(courseComboBox);
        controlPanel.add(addButton);
        mainPanel.add(controlPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.setVisible(true);
        
        return  mainPanel;
    }
}
