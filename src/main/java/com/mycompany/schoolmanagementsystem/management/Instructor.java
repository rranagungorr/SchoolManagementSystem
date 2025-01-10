/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.schoolmanagementsystem.management;

/**
 *
 * @author PC
 */
public class Instructor {

    private int instructorID;
    private String name;
    private String surname;
    private String email;
    private String gender;
    private Integer departmentID; // can be null
    private String username;
    private String password;

    public Instructor() {
    }

    public Instructor(int instructorID, String name, String surname,
            String email, String gender, Integer departmentID,
            String username, String password) {
        this.instructorID = instructorID;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.gender = gender;
        this.departmentID = departmentID;
        this.username = username;
        this.password = password;
    }

    public int getInstructorID() {
        return instructorID;
    }

    public void setInstructorID(int instructorID) {
        this.instructorID = instructorID;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    @Override
    public String toString() {
        return this.name + " " + this.surname; // Name ve Surname özelliklerini kullanın
    }
}
