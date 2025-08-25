package org.example;

import java.io.Serializable;
import java.util.Objects;

/**
 * Student class demonstrating OOP concepts:
 * - Encapsulation (private fields with getters/setters)
 * - Inheritance (extends Person)
 * - Interfaces (Comparable, Serializable)
 * - Proper equals, hashCode, and toString methods
 * - Comprehensive error handling and validation
 */
public class Student extends Person implements Comparable<Student>, Serializable {
    
    private static final long serialVersionUID = 1L;
    
    // Instance variables (encapsulation)
    private String studentId;
    private String course;
    private int yearOfStudy;
    private double gpa;
    
    // Static variable to track total students
    private static int totalStudents = 0;
    
    // Constants
    public static final int MIN_YEAR = 1;
    public static final int MAX_YEAR = 4;
    public static final double MIN_GPA = 0.0;
    public static final double MAX_GPA = 4.0;
    
    /**
     * Default constructor
     */
    public Student() {
        super();
        this.studentId = "";
        this.course = "";
        this.yearOfStudy = MIN_YEAR;
        this.gpa = MIN_GPA;
        totalStudents++;
    }
    
    /**
     * Parameterized constructor with comprehensive validation
     */
    public Student(String studentId, String firstName, String lastName, int age, 
                   String email, String course, int yearOfStudy, double gpa) {
        super(firstName, lastName, age, email);
        
        // Validate studentId
        if (studentId == null || studentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be null or empty");
        }
        this.studentId = studentId.trim();
        
        // Validate course
        if (course == null || course.trim().isEmpty()) {
            throw new IllegalArgumentException("Course cannot be null or empty");
        }
        this.course = course.trim();
        
        // Validate year of study and GPA
        setYearOfStudy(yearOfStudy);
        setGpa(gpa);
        
        totalStudents++;
    }
    
    // Getters and Setters (encapsulation)
    public String getStudentId() {
        return studentId;
    }
    
    public void setStudentId(String studentId) {
        if (studentId == null || studentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be null or empty");
        }
        this.studentId = studentId.trim();
    }
    
    public String getCourse() {
        return course;
    }
    
    public void setCourse(String course) {
        if (course == null || course.trim().isEmpty()) {
            throw new IllegalArgumentException("Course cannot be null or empty");
        }
        this.course = course.trim();
    }
    
    public int getYearOfStudy() {
        return yearOfStudy;
    }
    
    public void setYearOfStudy(int yearOfStudy) {
        if (yearOfStudy < MIN_YEAR || yearOfStudy > MAX_YEAR) {
            throw new IllegalArgumentException(
                String.format("Year of study must be between %d and %d. Received: %d", 
                            MIN_YEAR, MAX_YEAR, yearOfStudy));
        }
        this.yearOfStudy = yearOfStudy;
    }
    
    public double getGpa() {
        return gpa;
    }
    
    public void setGpa(double gpa) {
        if (gpa < MIN_GPA || gpa > MAX_GPA) {
            throw new IllegalArgumentException(
                String.format("GPA must be between %.1f and %.1f. Received: %.2f", 
                            MIN_GPA, MAX_GPA, gpa));
        }
        this.gpa = gpa;
    }
    
    // Static method
    public static int getTotalStudents() {
        return totalStudents;
    }
    
    // Business logic methods with validation
    public boolean isHonorsStudent() {
        return gpa >= 3.5;
    }
    
    public String getAcademicStatus() {
        if (gpa >= 3.5) return "Honors";
        else if (gpa >= 2.0) return "Good Standing";
        else return "Academic Warning";
    }
    
    /**
     * Validates if the student data is complete and valid
     * @return true if all required fields are valid
     * @throws IllegalStateException if validation fails
     */
    public boolean validateStudentData() {
        try {
            if (studentId == null || studentId.trim().isEmpty()) {
                throw new IllegalStateException("Student ID is missing");
            }
            if (course == null || course.trim().isEmpty()) {
                throw new IllegalStateException("Course is missing");
            }
            if (yearOfStudy < MIN_YEAR || yearOfStudy > MAX_YEAR) {
                throw new IllegalStateException("Invalid year of study");
            }
            if (gpa < MIN_GPA || gpa > MAX_GPA) {
                throw new IllegalStateException("Invalid GPA");
            }
            return true;
        } catch (Exception e) {
            throw new IllegalStateException("Student data validation failed: " + e.getMessage());
        }
    }
    
    // Override methods from Person class
    @Override
    public String getDisplayName() {
        try {
            return getFullName() + " (" + studentId + ")";
        } catch (Exception e) {
            return "Student [ID: " + studentId + "] - Error displaying name: " + e.getMessage();
        }
    }
    
    // Comparable interface implementation
    @Override
    public int compareTo(Student other) {
        if (other == null) {
            throw new IllegalArgumentException("Cannot compare with null student");
        }
        // Compare by GPA (descending order)
        return Double.compare(other.gpa, this.gpa);
    }
    
    // equals and hashCode methods
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        if (!super.equals(obj)) return false;
        
        Student student = (Student) obj;
        return Objects.equals(studentId, student.studentId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), studentId);
    }
    
    // toString method with error handling
    @Override
    public String toString() {
        try {
            return "Student{" +
                    "studentId='" + studentId + '\'' +
                    ", course='" + course + '\'' +
                    ", yearOfStudy=" + yearOfStudy +
                    ", gpa=" + gpa +
                    ", firstName='" + getFirstName() + '\'' +
                    ", lastName='" + getLastName() + '\'' +
                    ", email='" + getEmail() + '\'' +
                    '}';
        } catch (Exception e) {
            return "Student{studentId='" + studentId + "', error='toString failed: " + e.getMessage() + "'}";
        }
    }
}
