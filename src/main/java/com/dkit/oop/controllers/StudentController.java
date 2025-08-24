package com.dkit.oop.controllers;

import com.dkit.oop.dao.StudentDAO;
import com.dkit.oop.dao.StudentDAOImpl;
import com.dkit.oop.models.Student;
import com.dkit.oop.utils.JsonUtils;
import com.dkit.oop.utils.StudentComparator;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Controller class for managing student operations
 * Demonstrates MVC pattern and business logic coordination
 */
public class StudentController {
    
    private final StudentDAO studentDAO;
    
    public StudentController() {
        this.studentDAO = new StudentDAOImpl();
        initializeSampleData();
    }
    
    /**
     * Initialize the controller with sample data
     */
    private void initializeSampleData() {
        // Add some sample students
        addStudent("S001", "John", "Doe", LocalDate.of(2000, 5, 15), 
                   "john.doe@email.com", "Computer Science", 2, 3.8);
        addStudent("S002", "Jane", "Smith", LocalDate.of(1999, 8, 22), 
                   "jane.smith@email.com", "Computer Science", 3, 3.9);
        addStudent("S003", "Mike", "Johnson", LocalDate.of(2001, 3, 10), 
                   "mike.johnson@email.com", "Software Engineering", 1, 3.2);
        addStudent("S004", "Sarah", "Wilson", LocalDate.of(2000, 12, 5), 
                   "sarah.wilson@email.com", "Computer Science", 2, 3.7);
        addStudent("S005", "David", "Brown", LocalDate.of(1998, 7, 18), 
                   "david.brown@email.com", "Software Engineering", 4, 3.5);
    }
    
    /**
     * Add a new student
     * @param studentId the student ID
     * @param firstName the first name
     * @param lastName the last name
     * @param dateOfBirth the date of birth
     * @param email the email
     * @param course the course
     * @param yearOfStudy the year of study
     * @param gpa the GPA
     * @return true if successful, false otherwise
     */
    public boolean addStudent(String studentId, String firstName, String lastName, 
                             LocalDate dateOfBirth, String email, String course, 
                             int yearOfStudy, double gpa) {
        try {
            Student student = new Student(firstName, lastName, dateOfBirth, email, 
                                        studentId, course, yearOfStudy, gpa);
            return studentDAO.addStudent(student);
        } catch (IllegalArgumentException e) {
            System.err.println("Error adding student: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Find a student by ID
     * @param studentId the student ID to search for
     * @return Optional containing the student if found
     */
    public Optional<Student> findStudentById(String studentId) {
        return studentDAO.findStudentById(studentId);
    }
    
    /**
     * Get all students
     * @return List of all students
     */
    public List<Student> getAllStudents() {
        return studentDAO.getAllStudents();
    }
    
    /**
     * Get students sorted by GPA (descending)
     * @return List of students sorted by GPA
     */
    public List<Student> getStudentsSortedByGpa() {
        List<Student> students = studentDAO.getAllStudents();
        students.sort(StudentComparator.BY_GPA_DESC);
        return students;
    }
    
    /**
     * Get students sorted by name
     * @return List of students sorted by name
     */
    public List<Student> getStudentsSortedByName() {
        List<Student> students = studentDAO.getAllStudents();
        students.sort(StudentComparator.BY_NAME);
        return students;
    }
    
    /**
     * Get students by course
     * @param course the course to search for
     * @return List of students in the specified course
     */
    public List<Student> getStudentsByCourse(String course) {
        return studentDAO.findStudentsByCourse(course);
    }
    
    /**
     * Get students by year of study
     * @param year the year to search for
     * @return List of students in the specified year
     */
    public List<Student> getStudentsByYear(int year) {
        return studentDAO.findStudentsByYear(year);
    }
    
    /**
     * Get honors students (GPA >= 3.5)
     * @return List of honors students
     */
    public List<Student> getHonorsStudents() {
        return studentDAO.findStudentsWithGpaAbove(3.5);
    }
    
    /**
     * Search students by name
     * @param searchTerm the search term
     * @return List of matching students
     */
    public List<Student> searchStudentsByName(String searchTerm) {
        if (studentDAO instanceof StudentDAOImpl) {
            return ((StudentDAOImpl) studentDAO).searchStudentsByName(searchTerm);
        }
        return new java.util.ArrayList<>();
    }
    
    /**
     * Update a student
     * @param student the student to update
     * @return true if successful, false otherwise
     */
    public boolean updateStudent(Student student) {
        return studentDAO.updateStudent(student);
    }
    
    /**
     * Delete a student
     * @param studentId the student ID to delete
     * @return true if successful, false otherwise
     */
    public boolean deleteStudent(String studentId) {
        return studentDAO.deleteStudent(studentId);
    }
    
    /**
     * Get student statistics
     * @return String containing statistics
     */
    public String getStudentStatistics() {
        List<Student> students = studentDAO.getAllStudents();
        if (students.isEmpty()) {
            return "No students found.";
        }
        
        double averageGpa = students.stream()
                .mapToDouble(Student::getGpa)
                .average()
                .orElse(0.0);
        
        long honorsCount = students.stream()
                .filter(Student::isHonorsStudent)
                .count();
        
        return String.format("Total Students: %d\nAverage GPA: %.2f\nHonors Students: %d", 
                           students.size(), averageGpa, honorsCount);
    }
    
    /**
     * Export students to JSON file
     * @param filePath the file path to save to
     * @return true if successful, false otherwise
     */
    public boolean exportStudentsToJson(String filePath) {
        try {
            List<Student> students = studentDAO.getAllStudents();
            JsonUtils.saveStudentsToFile(students, filePath);
            return true;
        } catch (Exception e) {
            System.err.println("Error exporting students: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Get students grouped by course
     * @return Map of course name to list of students
     */
    public java.util.Map<String, List<Student>> getStudentsGroupedByCourse() {
        if (studentDAO instanceof StudentDAOImpl) {
            return ((StudentDAOImpl) studentDAO).getStudentsGroupedByCourse();
        }
        return new java.util.HashMap<>();
    }
    
    /**
     * Get students grouped by year
     * @return Map of year to list of students
     */
    public java.util.Map<Integer, List<Student>> getStudentsGroupedByYear() {
        if (studentDAO instanceof StudentDAOImpl) {
            return ((StudentDAOImpl) studentDAO).getStudentsGroupedByYear();
        }
        return new java.util.HashMap<>();
    }
}
