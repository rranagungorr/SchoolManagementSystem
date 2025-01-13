/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.schoolmanagementsystem.management;

/**
 *
 * @author PC
 */
public class Student {
    private int studentID;
    private String name;
    private String surname;
    private int credits;
    private int classLevel;
    private String email;
    private Integer departmentID; // can be null
    private String username;
    private String password;
    private String gender;

    public Student() {
    }

    public Student(int studentID, String name, String surname, 
                   int credits, int classLevel, String email, 
                   Integer departmentID, String username, 
                   String password, String gender) {
        this.studentID = studentID;
        this.name = name;
        this.surname = surname;
        this.credits = credits;
        this.classLevel = classLevel;
        this.email = email;
        this.departmentID = departmentID;
        this.username = username;
        this.password = password;
        this.gender = gender;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getClassLevel() {
        return classLevel;
    }

    public void setClassLevel(int classLevel) {
        this.classLevel = classLevel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(Integer departmentID) {
        this.departmentID = departmentID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
     @Override
    public String toString() {
        return this.name + " " + this.surname; // Name ve Surname özelliklerini kullanın
    }
}
