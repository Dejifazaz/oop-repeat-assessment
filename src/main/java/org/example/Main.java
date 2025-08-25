package org.example;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main application class for OOP Repeat Assessment Project
 * Demonstrates OOP concepts with console-based application
 * Includes comprehensive error handling and validation
 */
public class Main {
    
    public static void main(String[] args) {
        
        try {
            System.out.println("=== OOP Repeat Assessment Project ===");
            
            ArrayList<Student> studentList = new ArrayList<>();
            
            // Initialize with sample data
            initializeSampleData(studentList);
            
            // Display all students
            displayAllStudents(studentList);
            
            // Demonstrate OOP concepts
            demonstrateOOPConcepts(studentList);
            
            System.out.println("Finished, Goodbye!");
            
        } catch (Exception e) {
            System.err.println("Critical error in main application: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    /**
     * Initialize the student list with sample data
     * @param studentList - an ArrayList to be filled with Student objects
     */
    public static void initializeSampleData(ArrayList<Student> studentList) {
        
        if (studentList == null) {
            throw new IllegalArgumentException("Student list cannot be null");
        }
        
        System.out.println("Initializing sample data...");
        
        try {
            // Add students with proper error handling
            addStudentSafely(studentList, "S001", "John", "Doe", 20, "john.doe@email.com", "Computer Science", 2, 3.8);
            addStudentSafely(studentList, "S002", "Jane", "Smith", 21, "jane.smith@email.com", "Computer Science", 3, 3.9);
            addStudentSafely(studentList, "S003", "Mike", "Johnson", 19, "mike.johnson@email.com", "Software Engineering", 1, 3.2);
            addStudentSafely(studentList, "S004", "Sarah", "Wilson", 20, "sarah.wilson@email.com", "Computer Science", 2, 3.7);
            addStudentSafely(studentList, "S005", "David", "Brown", 22, "david.brown@email.com", "Software Engineering", 4, 3.5);
            
            System.out.println("Sample data loaded successfully. Total students: " + studentList.size());
            
        } catch (Exception e) {
            System.err.println("Error initializing sample data: " + e.getMessage());
            throw new RuntimeException("Failed to initialize sample data", e);
        }
    }
    
    /**
     * Safely add a student to the list with error handling
     */
    private static void addStudentSafely(ArrayList<Student> studentList, String studentId, String firstName, 
                                       String lastName, int age, String email, String course, int yearOfStudy, double gpa) {
        try {
            Student student = new Student(studentId, firstName, lastName, age, email, course, yearOfStudy, gpa);
            
            // Validate the student data
            if (student.validateStudentData()) {
                studentList.add(student);
            } else {
                System.err.println("Warning: Student " + studentId + " failed validation and was not added");
            }
            
        } catch (IllegalArgumentException e) {
            System.err.println("Error creating student " + studentId + ": " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error creating student " + studentId + ": " + e.getMessage());
        }
    }
    
    /**
     * Display all students in the list
     * @param studentList - ArrayList containing Student objects
     */
    public static void displayAllStudents(ArrayList<Student> studentList) {
        
        if (studentList == null) {
            System.err.println("Error: Student list is null");
            return;
        }
        
        if (studentList.isEmpty()) {
            System.out.println("No students to display.");
            return;
        }
        
        System.out.println("\nDisplaying all students:");
        int displayCount = 0;
        
        for (Student student : studentList) {
            try {
                if (student != null) {
                    System.out.println(student);
                    displayCount++;
                } else {
                    System.err.println("Warning: Found null student in list");
                }
            } catch (Exception e) {
                System.err.println("Error displaying student: " + e.getMessage());
            }
        }
        
        System.out.println("Successfully displayed " + displayCount + " students.");
    }
    
    /**
     * Demonstrate various OOP concepts
     * @param studentList - ArrayList containing Student objects
     */
    public static void demonstrateOOPConcepts(ArrayList<Student> studentList) {
        
        if (studentList == null || studentList.isEmpty()) {
            System.out.println("No students available for OOP demonstration.");
            return;
        }
        
        System.out.println("\n=== Demonstrating OOP Concepts ===");
        
        try {
            // Demonstrate inheritance and polymorphism
            demonstrateInheritanceAndPolymorphism(studentList);
            
            // Demonstrate collections and sorting
            demonstrateCollectionsAndSorting(studentList);
            
            // Demonstrate filtering
            demonstrateFiltering(studentList);
            
            // Demonstrate statistics
            demonstrateStatistics(studentList);
            
        } catch (Exception e) {
            System.err.println("Error during OOP demonstration: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrate inheritance and polymorphism
     */
    private static void demonstrateInheritanceAndPolymorphism(ArrayList<Student> studentList) {
        System.out.println("\n1. Inheritance and Polymorphism:");
        
        for (Student student : studentList) {
            try {
                if (student != null) {
                    System.out.println(student.getDisplayName() + " - Age: " + student.getAge() + 
                                     ", Status: " + student.getAcademicStatus());
                }
            } catch (Exception e) {
                System.err.println("Error displaying student info: " + e.getMessage());
            }
        }
    }
    
    /**
     * Demonstrate collections and sorting
     */
    private static void demonstrateCollectionsAndSorting(ArrayList<Student> studentList) {
        System.out.println("\n2. Collections and Sorting (by GPA):");
        
        try {
            // Create a copy to avoid modifying the original list
            ArrayList<Student> sortedList = new ArrayList<>(studentList);
            sortedList.sort((s1, s2) -> {
                try {
                    return Double.compare(s2.getGpa(), s1.getGpa());
                } catch (Exception e) {
                    System.err.println("Error comparing students: " + e.getMessage());
                    return 0;
                }
            });
            
            for (Student student : sortedList) {
                try {
                    if (student != null) {
                        System.out.println(student.getStudentId() + " - " + student.getFullName() + 
                                         " (GPA: " + student.getGpa() + ")");
                    }
                } catch (Exception e) {
                    System.err.println("Error displaying sorted student: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.err.println("Error during sorting: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrate filtering
     */
    private static void demonstrateFiltering(ArrayList<Student> studentList) {
        System.out.println("\n3. Filtering (Honors Students):");
        
        int honorsCount = 0;
        for (Student student : studentList) {
            try {
                if (student != null && student.isHonorsStudent()) {
                    System.out.println(student.getDisplayName() + " - Honors Student");
                    honorsCount++;
                }
            } catch (Exception e) {
                System.err.println("Error checking honors status: " + e.getMessage());
            }
        }
        
        if (honorsCount == 0) {
            System.out.println("No honors students found.");
        }
    }
    
    /**
     * Demonstrate statistics
     */
    private static void demonstrateStatistics(ArrayList<Student> studentList) {
        System.out.println("\n4. Statistics:");
        
        try {
            double averageGpa = studentList.stream()
                    .filter(student -> student != null)
                    .mapToDouble(student -> {
                        try {
                            return student.getGpa();
                        } catch (Exception e) {
                            System.err.println("Error getting GPA: " + e.getMessage());
                            return 0.0;
                        }
                    })
                    .average()
                    .orElse(0.0);
            
            System.out.println("Average GPA: " + String.format("%.2f", averageGpa));
            System.out.println("Total Students: " + studentList.size());
            
            // Additional statistics
            long validStudents = studentList.stream()
                    .filter(student -> student != null)
                    .count();
            System.out.println("Valid Students: " + validStudents);
            
        } catch (Exception e) {
            System.err.println("Error calculating statistics: " + e.getMessage());
        }
    }
}
