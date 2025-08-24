package com.dkit.oop.dao;

import com.dkit.oop.models.Student;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Implementation of StudentDAO interface using in-memory collections
 * Demonstrates collections usage, streams, and DAO pattern implementation
 */
public class StudentDAOImpl implements StudentDAO {
    
    // Using Map for efficient lookups by student ID
    private final Map<String, Student> studentsById;
    
    // Using Set to maintain unique students
    private final Set<Student> studentsSet;
    
    // Using List for ordered access
    private final List<Student> studentsList;
    
    public StudentDAOImpl() {
        this.studentsById = new HashMap<>();
        this.studentsSet = new HashSet<>();
        this.studentsList = new ArrayList<>();
    }
    
    @Override
    public boolean addStudent(Student student) {
        if (student == null || student.getStudentId() == null || student.getStudentId().trim().isEmpty()) {
            return false;
        }
        
        // Check if student already exists
        if (studentsById.containsKey(student.getStudentId())) {
            return false;
        }
        
        // Add to all collections
        studentsById.put(student.getStudentId(), student);
        studentsSet.add(student);
        studentsList.add(student);
        
        return true;
    }
    
    @Override
    public Optional<Student> findStudentById(String studentId) {
        if (studentId == null || studentId.trim().isEmpty()) {
            return Optional.empty();
        }
        
        Student student = studentsById.get(studentId);
        return Optional.ofNullable(student);
    }
    
    @Override
    public List<Student> findStudentsByCourse(String course) {
        if (course == null || course.trim().isEmpty()) {
            return new ArrayList<>();
        }
        
        return studentsList.stream()
                .filter(student -> course.equalsIgnoreCase(student.getCourse()))
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Student> findStudentsByYear(int year) {
        if (year < Student.MIN_YEAR || year > Student.MAX_YEAR) {
            return new ArrayList<>();
        }
        
        return studentsList.stream()
                .filter(student -> student.getYearOfStudy() == year)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Student> getAllStudents() {
        // Return a new list to prevent external modification
        return new ArrayList<>(studentsList);
    }
    
    @Override
    public boolean updateStudent(Student student) {
        if (student == null || student.getStudentId() == null || student.getStudentId().trim().isEmpty()) {
            return false;
        }
        
        // Check if student exists
        if (!studentsById.containsKey(student.getStudentId())) {
            return false;
        }
        
        // Remove old student from collections
        Student oldStudent = studentsById.get(student.getStudentId());
        studentsSet.remove(oldStudent);
        studentsList.remove(oldStudent);
        
        // Add updated student
        studentsById.put(student.getStudentId(), student);
        studentsSet.add(student);
        studentsList.add(student);
        
        return true;
    }
    
    @Override
    public boolean deleteStudent(String studentId) {
        if (studentId == null || studentId.trim().isEmpty()) {
            return false;
        }
        
        Student student = studentsById.remove(studentId);
        if (student != null) {
            studentsSet.remove(student);
            studentsList.remove(student);
            return true;
        }
        
        return false;
    }
    
    @Override
    public int getStudentCount() {
        return studentsList.size();
    }
    
    @Override
    public List<Student> findStudentsWithGpaAbove(double minGpa) {
        if (minGpa < Student.MIN_GPA || minGpa > Student.MAX_GPA) {
            return new ArrayList<>();
        }
        
        return studentsList.stream()
                .filter(student -> student.getGpa() >= minGpa)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Student> getStudentsSortedByGpa() {
        return studentsList.stream()
                .sorted() // Uses the compareTo method from Student class
                .collect(Collectors.toList());
    }
    
    // Additional utility methods demonstrating collections usage
    
    /**
     * Get students grouped by course
     * @return Map of course name to list of students
     */
    public Map<String, List<Student>> getStudentsGroupedByCourse() {
        return studentsList.stream()
                .collect(Collectors.groupingBy(Student::getCourse));
    }
    
    /**
     * Get students grouped by year of study
     * @return Map of year to list of students
     */
    public Map<Integer, List<Student>> getStudentsGroupedByYear() {
        return studentsList.stream()
                .collect(Collectors.groupingBy(Student::getYearOfStudy));
    }
    
    /**
     * Get average GPA for all students
     * @return average GPA
     */
    public double getAverageGpa() {
        if (studentsList.isEmpty()) {
            return 0.0;
        }
        
        return studentsList.stream()
                .mapToDouble(Student::getGpa)
                .average()
                .orElse(0.0);
    }
    
    /**
     * Get students with honors status
     * @return List of honors students
     */
    public List<Student> getHonorsStudents() {
        return studentsList.stream()
                .filter(Student::isHonorsStudent)
                .collect(Collectors.toList());
    }
    
    /**
     * Search students by name (case-insensitive)
     * @param searchTerm the search term
     * @return List of matching students
     */
    public List<Student> searchStudentsByName(String searchTerm) {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return new ArrayList<>();
        }
        
        String lowerSearchTerm = searchTerm.toLowerCase();
        
        return studentsList.stream()
                .filter(student -> 
                    student.getFirstName().toLowerCase().contains(lowerSearchTerm) ||
                    student.getLastName().toLowerCase().contains(lowerSearchTerm) ||
                    student.getFullName().toLowerCase().contains(lowerSearchTerm))
                .collect(Collectors.toList());
    }
}
