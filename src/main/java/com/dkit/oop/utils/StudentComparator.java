package com.dkit.oop.utils;

import com.dkit.oop.models.Student;
import java.util.Comparator;

/**
 * Comparator class demonstrating the Comparator interface
 * Provides different sorting strategies for Student objects
 */
public class StudentComparator {
    
    /**
     * Comparator to sort students by GPA (descending order)
     */
    public static final Comparator<Student> BY_GPA_DESC = new Comparator<Student>() {
        @Override
        public int compare(Student s1, Student s2) {
            return Double.compare(s2.getGpa(), s1.getGpa());
        }
    };
    
    /**
     * Comparator to sort students by GPA (ascending order)
     */
    public static final Comparator<Student> BY_GPA_ASC = new Comparator<Student>() {
        @Override
        public int compare(Student s1, Student s2) {
            return Double.compare(s1.getGpa(), s2.getGpa());
        }
    };
    
    /**
     * Comparator to sort students by last name, then first name
     */
    public static final Comparator<Student> BY_NAME = new Comparator<Student>() {
        @Override
        public int compare(Student s1, Student s2) {
            int lastNameCompare = s1.getLastName().compareToIgnoreCase(s2.getLastName());
            if (lastNameCompare != 0) {
                return lastNameCompare;
            }
            return s1.getFirstName().compareToIgnoreCase(s2.getFirstName());
        }
    };
    
    /**
     * Comparator to sort students by year of study, then by GPA
     */
    public static final Comparator<Student> BY_YEAR_THEN_GPA = new Comparator<Student>() {
        @Override
        public int compare(Student s1, Student s2) {
            int yearCompare = Integer.compare(s1.getYearOfStudy(), s2.getYearOfStudy());
            if (yearCompare != 0) {
                return yearCompare;
            }
            return Double.compare(s2.getGpa(), s1.getGpa());
        }
    };
    
    /**
     * Comparator to sort students by course, then by year, then by GPA
     */
    public static final Comparator<Student> BY_COURSE_YEAR_GPA = new Comparator<Student>() {
        @Override
        public int compare(Student s1, Student s2) {
            int courseCompare = s1.getCourse().compareToIgnoreCase(s2.getCourse());
            if (courseCompare != 0) {
                return courseCompare;
            }
            
            int yearCompare = Integer.compare(s1.getYearOfStudy(), s2.getYearOfStudy());
            if (yearCompare != 0) {
                return yearCompare;
            }
            
            return Double.compare(s2.getGpa(), s1.getGpa());
        }
    };
    
    /**
     * Comparator to sort students by age (youngest first)
     */
    public static final Comparator<Student> BY_AGE_ASC = new Comparator<Student>() {
        @Override
        public int compare(Student s1, Student s2) {
            return Integer.compare(s1.getAge(), s2.getAge());
        }
    };
    
    /**
     * Comparator to sort students by age (oldest first)
     */
    public static final Comparator<Student> BY_AGE_DESC = new Comparator<Student>() {
        @Override
        public int compare(Student s1, Student s2) {
            return Integer.compare(s2.getAge(), s1.getAge());
        }
    };
    
    /**
     * Comparator to sort students by student ID
     */
    public static final Comparator<Student> BY_STUDENT_ID = new Comparator<Student>() {
        @Override
        public int compare(Student s1, Student s2) {
            return s1.getStudentId().compareTo(s2.getStudentId());
        }
    };
    
    /**
     * Lambda-based comparator for sorting by email
     */
    public static final Comparator<Student> BY_EMAIL = (s1, s2) -> 
        s1.getEmail().compareToIgnoreCase(s2.getEmail());
    
    /**
     * Lambda-based comparator for sorting by academic status
     */
    public static final Comparator<Student> BY_ACADEMIC_STATUS = (s1, s2) -> {
        String status1 = s1.getAcademicStatus();
        String status2 = s2.getAcademicStatus();
        
        // Define priority order: Honors > Good Standing > Academic Warning
        int priority1 = getStatusPriority(status1);
        int priority2 = getStatusPriority(status2);
        
        return Integer.compare(priority1, priority2);
    };
    
    /**
     * Helper method to assign priority to academic status
     */
    private static int getStatusPriority(String status) {
        switch (status.toLowerCase()) {
            case "honors": return 1;
            case "good standing": return 2;
            case "academic warning": return 3;
            default: return 4;
        }
    }
    
    /**
     * Creates a custom comparator that combines multiple comparators
     * @param comparators array of comparators to chain
     * @return combined comparator
     */
    @SafeVarargs
    public static Comparator<Student> chain(Comparator<Student>... comparators) {
        if (comparators.length == 0) {
            return (s1, s2) -> 0;
        }
        
        Comparator<Student> result = comparators[0];
        for (int i = 1; i < comparators.length; i++) {
            result = result.thenComparing(comparators[i]);
        }
        
        return result;
    }
}
