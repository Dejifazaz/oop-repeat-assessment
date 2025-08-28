package org.example;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Statistics class for analyzing student data
 * Demonstrates utility methods and data analysis capabilities
 */
public class Statistics {
    
    private List<Student> students;
    
    public Statistics(List<Student> students) {
        this.students = students != null ? students : new ArrayList<>();
    }
    
    /**
     * Calculate average GPA across all students
     * @return average GPA or 0.0 if no students
     */
    public double calculateAverageGPA() {
        if (students.isEmpty()) {
            return 0.0;
        }
        
        double totalGPA = students.stream()
                .mapToDouble(Student::getGpa)
                .sum();
        
        return totalGPA / students.size();
    }
    
    /**
     * Find the highest GPA among all students
     * @return highest GPA or 0.0 if no students
     */
    public double getHighestGPA() {
        return students.stream()
                .mapToDouble(Student::getGpa)
                .max()
                .orElse(0.0);
    }
    
    /**
     * Find the lowest GPA among all students
     * @return lowest GPA or 0.0 if no students
     */
    public double getLowestGPA() {
        return students.stream()
                .mapToDouble(Student::getGpa)
                .min()
                .orElse(0.0);
    }
    
    /**
     * Calculate GPA standard deviation
     * @return standard deviation of GPAs
     */
    public double calculateGPAStandardDeviation() {
        if (students.size() < 2) {
            return 0.0;
        }
        
        double mean = calculateAverageGPA();
        double variance = students.stream()
                .mapToDouble(student -> Math.pow(student.getGpa() - mean, 2))
                .sum() / students.size();
        
        return Math.sqrt(variance);
    }
    
    /**
     * Get grade distribution (A, B, C, D, F)
     * @return Map with grade as key and count as value
     */
    public Map<String, Long> getGradeDistribution() {
        Map<String, Long> distribution = new LinkedHashMap<>();
        distribution.put("A", 0L);
        distribution.put("B", 0L);
        distribution.put("C", 0L);
        distribution.put("D", 0L);
        distribution.put("F", 0L);
        
        for (Student student : students) {
            String grade = getLetterGrade(student.getGpa());
            distribution.put(grade, distribution.get(grade) + 1);
        }
        
        return distribution;
    }
    
    /**
     * Convert GPA to letter grade
     * @param gpa student's GPA
     * @return letter grade (A, B, C, D, F)
     */
    private String getLetterGrade(double gpa) {
        if (gpa >= 3.7) return "A";
        else if (gpa >= 3.0) return "B";
        else if (gpa >= 2.0) return "C";
        else if (gpa >= 1.0) return "D";
        else return "F";
    }
    
    /**
     * Calculate average age of students
     * @return average age or 0 if no students
     */
    public double calculateAverageAge() {
        if (students.isEmpty()) {
            return 0.0;
        }
        
        double totalAge = students.stream()
                .mapToInt(Student::getAge)
                .sum();
        
        return (double) totalAge / students.size();
    }
    
    /**
     * Get age distribution by age groups
     * @return Map with age group as key and count as value
     */
    public Map<String, Long> getAgeDistribution() {
        Map<String, Long> distribution = new LinkedHashMap<>();
        distribution.put("18-20", 0L);
        distribution.put("21-25", 0L);
        distribution.put("26-30", 0L);
        distribution.put("31+", 0L);
        
        for (Student student : students) {
            int age = student.getAge();
            if (age <= 20) distribution.put("18-20", distribution.get("18-20") + 1);
            else if (age <= 25) distribution.put("21-25", distribution.get("21-25") + 1);
            else if (age <= 30) distribution.put("26-30", distribution.get("26-30") + 1);
            else distribution.put("31+", distribution.get("31+") + 1);
        }
        
        return distribution;
    }
    
    /**
     * Get students with GPA above a threshold
     * @param threshold minimum GPA
     * @return list of students with GPA >= threshold
     */
    public List<Student> getStudentsAboveGPA(double threshold) {
        return students.stream()
                .filter(student -> student.getGpa() >= threshold)
                .collect(Collectors.toList());
    }
    
    /**
     * Get students with GPA below a threshold
     * @param threshold maximum GPA
     * @return list of students with GPA < threshold
     */
    public List<Student> getStudentsBelowGPA(double threshold) {
        return students.stream()
                .filter(student -> student.getGpa() < threshold)
                .collect(Collectors.toList());
    }
    
    /**
     * Calculate GPA percentile
     * @param percentile percentile (0-100)
     * @return GPA value at that percentile
     */
    public double getGPAPercentile(double percentile) {
        if (students.isEmpty()) {
            return 0.0;
        }
        
        List<Double> gpas = students.stream()
                .map(Student::getGpa)
                .sorted()
                .collect(Collectors.toList());
        
        int index = (int) Math.ceil((percentile / 100.0) * gpas.size()) - 1;
        return gpas.get(Math.max(0, index));
    }
    
    /**
     * Get summary statistics as a formatted string
     * @return formatted statistics summary
     */
    public String getSummaryStatistics() {
        StringBuilder summary = new StringBuilder();
        summary.append("=== STUDENT STATISTICS SUMMARY ===\n");
        summary.append("Total Students: ").append(students.size()).append("\n");
        summary.append("Average GPA: ").append(String.format("%.2f", calculateAverageGPA())).append("\n");
        summary.append("Highest GPA: ").append(String.format("%.2f", getHighestGPA())).append("\n");
        summary.append("Lowest GPA: ").append(String.format("%.2f", getLowestGPA())).append("\n");
        summary.append("GPA Standard Deviation: ").append(String.format("%.2f", calculateGPAStandardDeviation())).append("\n");
        summary.append("Average Age: ").append(String.format("%.1f", calculateAverageAge())).append("\n");
        
        summary.append("\nGrade Distribution:\n");
        getGradeDistribution().forEach((grade, count) -> 
            summary.append(grade).append(": ").append(count).append(" students\n"));
        
        summary.append("\nAge Distribution:\n");
        getAgeDistribution().forEach((ageGroup, count) -> 
            summary.append(ageGroup).append(": ").append(count).append(" students\n"));
        
        return summary.toString();
    }
    
    /**
     * Update the student list
     * @param newStudents new list of students
     */
    public void updateStudents(List<Student> newStudents) {
        this.students = newStudents != null ? newStudents : new ArrayList<>();
    }
    
    /**
     * Get the current number of students
     * @return number of students
     */
    public int getStudentCount() {
        return students.size();
    }
}
