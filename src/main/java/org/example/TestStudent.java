package org.example;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Test class to demonstrate Student functionality
 * Similar to the CA1 example provided
 */
public class TestStudent {
    
    public static void main(String[] args) {
        
        System.out.println("=== Student Management System Test ===");
        
        ArrayList<Student> studentList = new ArrayList<>();
        
        // Load sample data
        loadStudentData(studentList);
        
        // Display all students
        displayAllStudents(studentList);
        
        // Test various methods
        testStudentMethods(studentList);
        
        // Test enhanced sorting methods
        testEnhancedSortingMethods(studentList);
        
        System.out.println("Test completed successfully!");
    }
    
    /**
     * Load sample student data into the list
     * @param studentList - ArrayList to be filled with Student objects
     */
    public static void loadStudentData(ArrayList<Student> studentList) {
        
        System.out.println("Loading student data...");
        
        studentList.add(new Student("S001", "John", "Doe", 20, "john.doe@email.com", "Computer Science", 2, 3.8));
        studentList.add(new Student("S002", "Jane", "Smith", 21, "jane.smith@email.com", "Computer Science", 3, 3.9));
        studentList.add(new Student("S003", "Mike", "Johnson", 19, "mike.johnson@email.com", "Software Engineering", 1, 3.2));
        studentList.add(new Student("S004", "Sarah", "Wilson", 20, "sarah.wilson@email.com", "Computer Science", 2, 3.7));
        studentList.add(new Student("S005", "David", "Brown", 22, "david.brown@email.com", "Software Engineering", 4, 3.5));
        studentList.add(new Student("S006", "Emily", "Davis", 18, "emily.davis@email.com", "Computer Science", 1, 3.6));
        studentList.add(new Student("S007", "Michael", "Wilson", 21, "michael.wilson@email.com", "Software Engineering", 3, 3.4));
        studentList.add(new Student("S008", "Lisa", "Anderson", 20, "lisa.anderson@email.com", "Computer Science", 2, 3.85));
        
        System.out.println("Loaded " + studentList.size() + " students.");
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
     * Test various student methods and operations
     * @param studentList - ArrayList containing Student objects
     */
    public static void testStudentMethods(ArrayList<Student> studentList) {
        
        System.out.println("\n=== Testing Student Methods ===");
        
        // Test 1: Sort students by GPA (descending)
        System.out.println("\n1. Students sorted by GPA (descending):");
        studentList.sort((s1, s2) -> Double.compare(s2.getGpa(), s1.getGpa()));
        for (Student student : studentList) {
            System.out.println(student.getStudentId() + " - " + student.getFullName() + " (GPA: " + student.getGpa() + ")");
        }
        
        // Test 2: Sort students by name
        System.out.println("\n2. Students sorted by name:");
        studentList.sort((s1, s2) -> s1.getFullName().compareTo(s2.getFullName()));
        for (Student student : studentList) {
            System.out.println(student.getDisplayName());
        }
        
        // Test 3: Find honors students
        System.out.println("\n3. Honors students (GPA >= 3.5):");
        for (Student student : studentList) {
            if (student.isHonorsStudent()) {
                System.out.println(student.getDisplayName() + " - Honors");
            }
        }
        
        // Test 4: Students by course
        System.out.println("\n4. Students by course (Computer Science):");
        for (Student student : studentList) {
            if (student.getCourse().equals("Computer Science")) {
                System.out.println(student.getDisplayName() + " - " + student.getCourse());
            }
        }
        
        // Test 5: Students by year
        System.out.println("\n5. Students by year (2nd year):");
        for (Student student : studentList) {
            if (student.getYearOfStudy() == 2) {
                System.out.println(student.getDisplayName() + " - Year " + student.getYearOfStudy());
            }
        }
        
        // Test 6: Calculate statistics
        System.out.println("\n6. Student statistics:");
        double totalGpa = 0;
        int honorsCount = 0;
        for (Student student : studentList) {
            totalGpa += student.getGpa();
            if (student.isHonorsStudent()) {
                honorsCount++;
            }
        }
        double averageGpa = totalGpa / studentList.size();
        System.out.println("Total students: " + studentList.size());
        System.out.println("Average GPA: " + String.format("%.2f", averageGpa));
        System.out.println("Honors students: " + honorsCount);
        
        // Test 7: Search by name
        System.out.println("\n7. Search students by name (contains 'John'):");
        for (Student student : studentList) {
            if (student.getFirstName().contains("John") || student.getLastName().contains("John")) {
                System.out.println("Found: " + student.getDisplayName());
            }
        }
        
        // Test 8: Demonstrate inheritance
        System.out.println("\n8. Inheritance demonstration:");
        for (Student student : studentList) {
            System.out.println(student.getDisplayName() + " - Age: " + student.getAge() + 
                             ", Adult: " + student.isAdult() + ", Status: " + student.getAcademicStatus());
        }
    }
    
    /**
     * Test enhanced sorting methods with different Comparator implementations
     * @param studentList - ArrayList containing Student objects
     */
    public static void testEnhancedSortingMethods(ArrayList<Student> studentList) {
        
        System.out.println("\n=== Enhanced Sorting Methods ===");
        
        // Create a copy of the list for sorting tests
        ArrayList<Student> testList = new ArrayList<>(studentList);
        
        // Test 1: Sort by age (ascending)
        System.out.println("\n1. Students sorted by age (ascending):");
        testList.sort((s1, s2) -> Integer.compare(s1.getAge(), s2.getAge()));
        for (Student student : testList) {
            System.out.println(student.getStudentId() + " - " + student.getFullName() + " (Age: " + student.getAge() + ")");
        }
        
        // Test 2: Sort by age (descending)
        System.out.println("\n2. Students sorted by age (descending):");
        testList.sort((s1, s2) -> Integer.compare(s2.getAge(), s1.getAge()));
        for (Student student : testList) {
            System.out.println(student.getStudentId() + " - " + student.getFullName() + " (Age: " + student.getAge() + ")");
        }
        
        // Test 3: Sort by last name, then first name
        System.out.println("\n3. Students sorted by last name, then first name:");
        testList.sort((s1, s2) -> {
            int lastNameCompare = s1.getLastName().compareTo(s2.getLastName());
            if (lastNameCompare != 0) {
                return lastNameCompare;
            }
            return s1.getFirstName().compareTo(s2.getFirstName());
        });
        for (Student student : testList) {
            System.out.println(student.getDisplayName());
        }
        
        // Test 4: Sort by course, then by GPA (descending)
        System.out.println("\n4. Students sorted by course, then by GPA (descending):");
        testList.sort((s1, s2) -> {
            int courseCompare = s1.getCourse().compareTo(s2.getCourse());
            if (courseCompare != 0) {
                return courseCompare;
            }
            return Double.compare(s2.getGpa(), s1.getGpa());
        });
        for (Student student : testList) {
            System.out.println(student.getStudentId() + " - " + student.getFullName() + 
                             " (" + student.getCourse() + ", GPA: " + student.getGpa() + ")");
        }
        
        // Test 5: Sort by year of study, then by age
        System.out.println("\n5. Students sorted by year of study, then by age:");
        testList.sort((s1, s2) -> {
            int yearCompare = Integer.compare(s1.getYearOfStudy(), s2.getYearOfStudy());
            if (yearCompare != 0) {
                return yearCompare;
            }
            return Integer.compare(s1.getAge(), s2.getAge());
        });
        for (Student student : testList) {
            System.out.println(student.getStudentId() + " - " + student.getFullName() + 
                             " (Year: " + student.getYearOfStudy() + ", Age: " + student.getAge() + ")");
        }
        
        // Test 6: Sort by academic status (Honors first, then Good Standing, then Academic Warning)
        System.out.println("\n6. Students sorted by academic status:");
        testList.sort((s1, s2) -> {
            String status1 = s1.getAcademicStatus();
            String status2 = s2.getAcademicStatus();
            
            // Define priority: Honors > Good Standing > Academic Warning
            int priority1 = getStatusPriority(status1);
            int priority2 = getStatusPriority(status2);
            
            return Integer.compare(priority1, priority2);
        });
        for (Student student : testList) {
            System.out.println(student.getStudentId() + " - " + student.getFullName() + 
                             " (Status: " + student.getAcademicStatus() + ")");
        }
        
        // Test 7: Sort by student ID (alphabetical)
        System.out.println("\n7. Students sorted by student ID:");
        testList.sort((s1, s2) -> s1.getStudentId().compareTo(s2.getStudentId()));
        for (Student student : testList) {
            System.out.println(student.getStudentId() + " - " + student.getDisplayName());
        }
        
        // Test 8: Sort by email domain (extract domain and sort)
        System.out.println("\n8. Students sorted by email domain:");
        testList.sort((s1, s2) -> {
            String domain1 = s1.getEmail().substring(s1.getEmail().indexOf('@') + 1);
            String domain2 = s2.getEmail().substring(s2.getEmail().indexOf('@') + 1);
            return domain1.compareTo(domain2);
        });
        for (Student student : testList) {
            System.out.println(student.getStudentId() + " - " + student.getFullName() + 
                             " (Email: " + student.getEmail() + ")");
        }
    }
    
    /**
     * Helper method to assign priority to academic status
     * @param status the academic status
     * @return priority value (lower = higher priority)
     */
    private static int getStatusPriority(String status) {
        switch (status.toLowerCase()) {
            case "honors": return 1;
            case "good standing": return 2;
            case "academic warning": return 3;
            default: return 4;
        }
    }
}
