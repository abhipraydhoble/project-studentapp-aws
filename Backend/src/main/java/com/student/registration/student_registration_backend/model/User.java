package com.student.registration.student_registration_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table; // 1. ADD THIS IMPORT
import lombok.Data;

@Entity
@Table(name = "students") // 2. ADD THIS TO FORCE MAP TO STUDENTS TABLE
@Data 
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String course;
    private String studentClass;
    private Double percentage;
    private String branch;
    private String mobileNumber;
}
