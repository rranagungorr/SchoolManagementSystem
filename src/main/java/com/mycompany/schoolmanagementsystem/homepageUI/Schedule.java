/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.schoolmanagementsystem.homepageUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Merve
 */
public class Schedule extends javax.swing.JPanel {

    public Schedule() {
        initComponents();
        // Başlık stilini ayarla
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(102, 0, 102)); // Dark purple
        titleLabel.setText("Course Schedule");

        // Tablo için model tanımla
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
        scheduleTable.setModel(model);
        scheduleTable.setRowHeight(50);
        scheduleTable.setFont(new Font("Arial", Font.PLAIN, 14));
        scheduleTable.setGridColor(new Color(0, 51, 102)); // Dark blue grid
        scheduleTable.setShowGrid(true);

// Tablo başlıklarını daha koyu yap
        JTableHeader tableHeader = scheduleTable.getTableHeader();
        tableHeader.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                label.setBackground(new Color(0, 51, 102)); // Dark blue background
                label.setForeground(Color.WHITE); // White text
                label.setFont(new Font("Arial", Font.BOLD, 14)); // Bold font
                label.setHorizontalAlignment(SwingConstants.CENTER); // Center alignment
                return label;

            }
        });

// Seçili hücre rengini açık mavi yap
        scheduleTable.setSelectionBackground(new Color(173, 216, 230)); // Light blue for selection

// ComboBox seçeneklerini ayarla
        courseComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"Math", "Physics", "Computer Sciencnce", "History", "Biology"}));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titleLabel2 = new javax.swing.JLabel();
        courseComboBox2 = new javax.swing.JComboBox<>();
        addCourseButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        scheduleTable = new javax.swing.JTable();

        titleLabel2.setText("sdasdsd");

        courseComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        addCourseButton2.setText("jButton1");
        addCourseButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCourseButton2ActionPerformed(evt);
            }
        });

        scheduleTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        scheduleTable.setFocusTraversalPolicyProvider(true);
        scheduleTable.setGridColor(new java.awt.Color(0, 204, 51));
        jScrollPane1.setViewportView(scheduleTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(courseComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addCourseButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 125, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 698, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(164, 164, 164))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(titleLabel2)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(170, 170, 170)
                        .addComponent(courseComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(addCourseButton2)))
                .addContainerGap(179, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(titleLabel2)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addCourseButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addCourseButton2ActionPerformed
        int selectedRow = scheduleTable.getSelectedRow();
        int selectedCol = scheduleTable.getSelectedColumn();

        if (selectedRow == -1 || selectedCol == -1 || selectedCol == 0) { // Time sütununu hariç tut
            JOptionPane.showMessageDialog(null, "Please select a valid cell (not the Time column).", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String selectedCourse = (String) courseComboBox.getSelectedItem();
        int courseHours = 3; // Example: All courses have 3 hours
        String classroom = " H214"; // Example classroom

        // Yeterli boş hücre var mı kontrol et
        boolean hasEnoughCells = true;
        for (int i = selectedRow; i < selectedRow + courseHours; i++) {
            if (i >= scheduleTable.getRowCount() || !((String) scheduleTable.getValueAt(i, selectedCol)).isEmpty()) {
                hasEnoughCells = false;
                break;
            }
        }

        if (!hasEnoughCells) {
            JOptionPane.showMessageDialog(null, "Not enough consecutive cells for this course.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Ders ve sınıf bilgisini hücrelere ekle
        for (int i = selectedRow; i < selectedRow + courseHours; i++) {
            scheduleTable.setValueAt(selectedCourse + "\n" + classroom, i, selectedCol);
        }
    }//GEN-LAST:event_addCourseButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addCourseButton;
    private javax.swing.JButton addCourseButton1;
    private javax.swing.JButton addCourseButton2;
    private javax.swing.JComboBox<String> courseComboBox;
    private javax.swing.JComboBox<String> courseComboBox1;
    private javax.swing.JComboBox<String> courseComboBox2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable scheduleTable;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JLabel titleLabel1;
    private javax.swing.JLabel titleLabel2;
    // End of variables declaration//GEN-END:variables
}
