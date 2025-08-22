package org.example;

import java.io.Serializable;
import java.util.Objects;

/**
 * Student class demonstrating OOP concepts:
 * - Encapsulation (private fields with getters/setters)
 * - Inheritance (extends Person)
 * - Interfaces (Comparable, Serializable)
 * - Proper equals, hashCode, and toString methods
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
     * Parameterized constructor
     */
    public Student(String studentId, String firstName, String lastName, int age, 
                   String email, String course, int yearOfStudy, double gpa) {
        super(firstName, lastName, age, email);
        this.studentId = studentId;
        this.course = course;
        setYearOfStudy(yearOfStudy);
        setGpa(gpa);
        totalStudents++;
    }
    
    // Getters and Setters (encapsulation)
    public String getStudentId() {
        return studentId;
    }
    
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    
    public String getCourse() {
        return course;
    }
    
    public void setCourse(String course) {
        this.course = course;
    }
    
    public int getYearOfStudy() {
        return yearOfStudy;
    }
    
    public void setYearOfStudy(int yearOfStudy) {
        if (yearOfStudy >= MIN_YEAR && yearOfStudy <= MAX_YEAR) {
            this.yearOfStudy = yearOfStudy;
        } else {
            throw new IllegalArgumentException("Year of study must be between " + MIN_YEAR + " and " + MAX_YEAR);
        }
    }
    
    public double getGpa() {
        return gpa;
    }
    
    public void setGpa(double gpa) {
        if (gpa >= MIN_GPA && gpa <= MAX_GPA) {
            this.gpa = gpa;
        } else {
            throw new IllegalArgumentException("GPA must be between " + MIN_GPA + " and " + MAX_GPA);
        }
    }
    
    // Static method
    public static int getTotalStudents() {
        return totalStudents;
    }
    
    // Business logic methods
    public boolean isHonorsStudent() {
        return gpa >= 3.5;
    }
    
    public String getAcademicStatus() {
        if (gpa >= 3.5) return "Honors";
        else if (gpa >= 2.0) return "Good Standing";
        else return "Academic Warning";
    }
    
    // Override methods from Person class
    @Override
    public String getDisplayName() {
        return super.getDisplayName() + " (" + studentId + ")";
    }
    
    // Comparable interface implementation
    @Override
    public int compareTo(Student other) {
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
    
    // toString method
    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", course='" + course + '\'' +
                ", yearOfStudy=" + yearOfStudy +
                ", gpa=" + gpa +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", email='" + getEmail() + '\'' +
                '}';
    }
}
