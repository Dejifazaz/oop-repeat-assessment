package com.dkit.oop;

import com.dkit.oop.controllers.StudentController;
import com.dkit.oop.models.Student;
import com.dkit.oop.utils.JsonUtils;
import com.dkit.oop.utils.StudentComparator;

import java.time.LocalDate;
import java.util.List;

/**
 * Test application to demonstrate all OOP concepts implemented in the project
 * This class showcases the functionality without requiring JavaFX
 */
public class TestApplication {
    
    public static void main(String[] args) {
        System.out.println("=== OOP Repeat Assessment Project - Test Application ===\n");
        
        // Create student controller
        StudentController controller = new StudentController();
        
        // Test 1: Display all students
        System.out.println("1. All Students:");
        List<Student> allStudents = controller.getAllStudents();
        displayStudents(allStudents);
        
        // Test 2: Demonstrate sorting with Comparators
        System.out.println("\n2. Students sorted by GPA (descending):");
        List<Student> studentsByGpa = controller.getStudentsSortedByGpa();
        displayStudents(studentsByGpa);
        
        System.out.println("\n3. Students sorted by name:");
        List<Student> studentsByName = controller.getStudentsSortedByName();
        displayStudents(studentsByName);
        
        // Test 3: Demonstrate filtering
        System.out.println("\n4. Students by course (Computer Science):");
        List<Student> csStudents = controller.getStudentsByCourse("Computer Science");
        displayStudents(csStudents);
        
        System.out.println("\n5. Students by year (2nd year):");
        List<Student> year2Students = controller.getStudentsByYear(2);
        displayStudents(year2Students);
        
        System.out.println("\n6. Honors students (GPA >= 3.5):");
        List<Student> honorsStudents = controller.getHonorsStudents();
        displayStudents(honorsStudents);
        
        // Test 4: Demonstrate search functionality
        System.out.println("\n7. Search students by name (contains 'John'):");
        List<Student> searchResults = controller.searchStudentsByName("John");
        displayStudents(searchResults);
        
        // Test 5: Demonstrate JSON serialization
        System.out.println("\n8. JSON Serialization Test:");
        String jsonOutput = JsonUtils.studentsToJson(allStudents);
        System.out.println("JSON output (first 200 characters):");
        System.out.println(jsonOutput.substring(0, Math.min(200, jsonOutput.length())) + "...");
        
        // Test 6: Demonstrate statistics
        System.out.println("\n9. Student Statistics:");
        String stats = controller.getStudentStatistics();
        System.out.println(stats);
        
        // Test 7: Demonstrate grouping
        System.out.println("\n10. Students grouped by course:");
        var groupedByCourse = controller.getStudentsGroupedByCourse();
        groupedByCourse.forEach((course, students) -> {
            System.out.println(course + " (" + students.size() + " students):");
            students.forEach(student -> 
                System.out.println("  - " + student.getDisplayName() + " (GPA: " + student.getGpa() + ")"));
        });
        
        // Test 8: Demonstrate inheritance and polymorphism
        System.out.println("\n11. Inheritance and Polymorphism Test:");
        Student testStudent = allStudents.get(0);
        System.out.println("Student: " + testStudent.getDisplayName());
        System.out.println("Is adult: " + testStudent.isAdult());
        System.out.println("Age: " + testStudent.getAge());
        System.out.println("Academic status: " + testStudent.getAcademicStatus());
        System.out.println("Is honors student: " + testStudent.isHonorsStudent());
        
        // Test 9: Demonstrate collections operations
        System.out.println("\n12. Collections Operations Test:");
        System.out.println("Total students: " + Student.getTotalStudents());
        System.out.println("Average GPA: " + String.format("%.2f", 
            allStudents.stream().mapToDouble(Student::getGpa).average().orElse(0.0)));
        
        // Test 10: Demonstrate error handling
        System.out.println("\n13. Error Handling Test:");
        try {
            Student invalidStudent = new Student("Test", "Student", LocalDate.now(), 
                                               "test@email.com", "INVALID", "Test Course", 5, 5.0);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught expected error: " + e.getMessage());
        }
        
        System.out.println("\n=== Test Application Completed Successfully ===");
        System.out.println("\nOOP Concepts Demonstrated:");
        System.out.println("✓ Encapsulation (private fields with getters/setters)");
        System.out.println("✓ Inheritance (Student extends Person)");
        System.out.println("✓ Polymorphism (abstract methods, method overriding)");
        System.out.println("✓ Interfaces (Comparable, Serializable, DAO pattern)");
        System.out.println("✓ Collections (List, Map, Set, Streams)");
        System.out.println("✓ Comparators (multiple sorting strategies)");
        System.out.println("✓ Exception handling");
        System.out.println("✓ JSON serialization");
        System.out.println("✓ Threading and concurrency (in network classes)");
        System.out.println("✓ Socket programming (client-server)");
        System.out.println("✓ MVC pattern (Model-View-Controller)");
        System.out.println("✓ DAO pattern (Data Access Object)");
    }
    
    /**
     * Helper method to display a list of students
     */
    private static void displayStudents(List<Student> students) {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        
        students.forEach(student -> {
            System.out.printf("  %s - %s %s (Course: %s, Year: %d, GPA: %.2f, Status: %s)%n",
                student.getStudentId(),
                student.getFirstName(),
                student.getLastName(),
                student.getCourse(),
                student.getYearOfStudy(),
                student.getGpa(),
                student.getAcademicStatus());
        });
    }
}
