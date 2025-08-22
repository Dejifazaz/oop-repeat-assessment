package org.example;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main application class for OOP Repeat Assessment Project
 * Demonstrates OOP concepts with console-based application
 */
public class Main {
    
    public static void main(String[] args) {
        
        System.out.println("=== OOP Repeat Assessment Project ===");
        
        ArrayList<Student> studentList = new ArrayList<>();
        
        // Initialize with sample data
        initializeSampleData(studentList);
        
        // Display all students
        displayAllStudents(studentList);
        
        // Demonstrate OOP concepts
        demonstrateOOPConcepts(studentList);
        
        System.out.println("Finished, Goodbye!");
    }
    
    /**
     * Initialize the student list with sample data
     * @param studentList - an ArrayList to be filled with Student objects
     */
    public static void initializeSampleData(ArrayList<Student> studentList) {
        
        System.out.println("Initializing sample data...");
        
        studentList.add(new Student("S001", "John", "Doe", 20, "john.doe@email.com", "Computer Science", 2, 3.8));
        studentList.add(new Student("S002", "Jane", "Smith", 21, "jane.smith@email.com", "Computer Science", 3, 3.9));
        studentList.add(new Student("S003", "Mike", "Johnson", 19, "mike.johnson@email.com", "Software Engineering", 1, 3.2));
        studentList.add(new Student("S004", "Sarah", "Wilson", 20, "sarah.wilson@email.com", "Computer Science", 2, 3.7));
        studentList.add(new Student("S005", "David", "Brown", 22, "david.brown@email.com", "Software Engineering", 4, 3.5));
        
        System.out.println("Sample data loaded successfully.");
    }
    
    /**
     * Display all students in the list
     * @param studentList - ArrayList containing Student objects
     */
    public static void displayAllStudents(ArrayList<Student> studentList) {
        System.out.println("\nDisplaying all students:");
        for (Student student : studentList) {
            System.out.println(student);
        }
    }
    
    /**
     * Demonstrate various OOP concepts
     * @param studentList - ArrayList containing Student objects
     */
    public static void demonstrateOOPConcepts(ArrayList<Student> studentList) {
        
        System.out.println("\n=== Demonstrating OOP Concepts ===");
        
        // Demonstrate inheritance and polymorphism
        System.out.println("\n1. Inheritance and Polymorphism:");
        for (Student student : studentList) {
            System.out.println(student.getDisplayName() + " - Age: " + student.getAge() + 
                             ", Status: " + student.getAcademicStatus());
        }
        
        // Demonstrate collections and sorting
        System.out.println("\n2. Collections and Sorting (by GPA):");
        studentList.sort((s1, s2) -> Double.compare(s2.getGpa(), s1.getGpa()));
        for (Student student : studentList) {
            System.out.println(student.getStudentId() + " - " + student.getFullName() + 
                             " (GPA: " + student.getGpa() + ")");
        }
        
        // Demonstrate filtering
        System.out.println("\n3. Filtering (Honors Students):");
        for (Student student : studentList) {
            if (student.isHonorsStudent()) {
                System.out.println(student.getDisplayName() + " - Honors Student");
            }
        }
        
        // Demonstrate statistics
        System.out.println("\n4. Statistics:");
        double averageGpa = studentList.stream()
                .mapToDouble(Student::getGpa)
                .average()
                .orElse(0.0);
        System.out.println("Average GPA: " + String.format("%.2f", averageGpa));
        System.out.println("Total Students: " + studentList.size());
    }
}
