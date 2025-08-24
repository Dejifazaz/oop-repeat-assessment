package com.dkit.oop.dao;

import com.dkit.oop.models.Student;
import java.util.List;
import java.util.Optional;

/**
 * Data Access Object interface for Student entities
 * Demonstrates the DAO pattern and interface usage
 */
public interface StudentDAO {
    
    /**
     * Add a new student to the database
     * @param student the student to add
     * @return true if successful, false otherwise
     */
    boolean addStudent(Student student);
    
    /**
     * Find a student by their ID
     * @param studentId the student ID to search for
     * @return Optional containing the student if found
     */
    Optional<Student> findStudentById(String studentId);
    
    /**
     * Find students by course
     * @param course the course to search for
     * @return List of students in the specified course
     */
    List<Student> findStudentsByCourse(String course);
    
    /**
     * Find students by year of study
     * @param year the year to search for
     * @return List of students in the specified year
     */
    List<Student> findStudentsByYear(int year);
    
    /**
     * Get all students
     * @return List of all students
     */
    List<Student> getAllStudents();
    
    /**
     * Update an existing student
     * @param student the student to update
     * @return true if successful, false otherwise
     */
    boolean updateStudent(Student student);
    
    /**
     * Delete a student by their ID
     * @param studentId the student ID to delete
     * @return true if successful, false otherwise
     */
    boolean deleteStudent(String studentId);
    
    /**
     * Get the total number of students
     * @return the total count
     */
    int getStudentCount();
    
    /**
     * Find students with GPA above a certain threshold
     * @param minGpa the minimum GPA threshold
     * @return List of students meeting the criteria
     */
    List<Student> findStudentsWithGpaAbove(double minGpa);
    
    /**
     * Get students sorted by GPA (descending)
     * @return List of students sorted by GPA
     */
    List<Student> getStudentsSortedByGpa();
}
