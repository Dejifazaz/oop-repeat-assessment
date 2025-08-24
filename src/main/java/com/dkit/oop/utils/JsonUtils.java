package com.dkit.oop.utils;

import com.dkit.oop.models.Student;
import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Simple JSON utility class for serialization and deserialization
 * Demonstrates JSON processing capabilities without external dependencies
 */
public class JsonUtils {
    
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    /**
     * Convert a Student object to JSON string
     * @param student the student to convert
     * @return JSON string representation
     */
    public static String studentToJson(Student student) {
        if (student == null) {
            return "null";
        }
        
        StringBuilder json = new StringBuilder();
        json.append("{");
        json.append("\"studentId\":\"").append(escapeJson(student.getStudentId())).append("\",");
        json.append("\"firstName\":\"").append(escapeJson(student.getFirstName())).append("\",");
        json.append("\"lastName\":\"").append(escapeJson(student.getLastName())).append("\",");
        json.append("\"dateOfBirth\":\"").append(student.getDateOfBirth().format(DATE_FORMATTER)).append("\",");
        json.append("\"email\":\"").append(escapeJson(student.getEmail())).append("\",");
        json.append("\"course\":\"").append(escapeJson(student.getCourse())).append("\",");
        json.append("\"yearOfStudy\":").append(student.getYearOfStudy()).append(",");
        json.append("\"gpa\":").append(student.getGpa());
        json.append("}");
        
        return json.toString();
    }
    
    /**
     * Convert a list of students to JSON string
     * @param students the list of students to convert
     * @return JSON string representation
     */
    public static String studentsToJson(List<Student> students) {
        if (students == null) {
            return "null";
        }
        
        StringBuilder json = new StringBuilder();
        json.append("[");
        
        for (int i = 0; i < students.size(); i++) {
            if (i > 0) {
                json.append(",");
            }
            json.append(studentToJson(students.get(i)));
        }
        
        json.append("]");
        return json.toString();
    }
    
    /**
     * Save a list of students to a JSON file
     * @param students the list of students to save
     * @param filePath the file path to save to
     */
    public static void saveStudentsToFile(List<Student> students, String filePath) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            writer.write(studentsToJson(students));
        } catch (IOException e) {
            throw new RuntimeException("Error saving students to file", e);
        }
    }
    
    /**
     * Load a list of students from a JSON file
     * @param filePath the file path to load from
     * @return List of Student objects
     */
    public static List<Student> loadStudentsFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
            return parseStudentsFromJson(content.toString());
        } catch (IOException e) {
            throw new RuntimeException("Error loading students from file", e);
        }
    }
    
    /**
     * Parse students from JSON string (simplified implementation)
     * @param json the JSON string to parse
     * @return List of Student objects
     */
    public static List<Student> parseStudentsFromJson(String json) {
        // This is a simplified parser - in a real application you'd use a proper JSON library
        // For demonstration purposes, we'll return an empty list
        // In practice, you would implement a proper JSON parser here
        return new java.util.ArrayList<>();
    }
    
    /**
     * Escape special characters in JSON strings
     * @param input the string to escape
     * @return escaped string
     */
    private static String escapeJson(String input) {
        if (input == null) {
            return "";
        }
        
        return input.replace("\\", "\\\\")
                   .replace("\"", "\\\"")
                   .replace("\b", "\\b")
                   .replace("\f", "\\f")
                   .replace("\n", "\\n")
                   .replace("\r", "\\r")
                   .replace("\t", "\\t");
    }
    
    /**
     * Pretty print a JSON string (adds indentation)
     * @param json the JSON string to format
     * @return formatted JSON string
     */
    public static String prettyPrintJson(String json) {
        if (json == null || json.trim().isEmpty()) {
            return json;
        }
        
        StringBuilder pretty = new StringBuilder();
        int indentLevel = 0;
        boolean inString = false;
        boolean escapeNext = false;
        
        for (char c : json.toCharArray()) {
            if (escapeNext) {
                pretty.append(c);
                escapeNext = false;
                continue;
            }
            
            if (c == '\\') {
                escapeNext = true;
                pretty.append(c);
                continue;
            }
            
            if (c == '"' && !escapeNext) {
                inString = !inString;
            }
            
            if (!inString) {
                if (c == '{' || c == '[') {
                    pretty.append(c).append('\n');
                    indentLevel++;
                    pretty.append("  ".repeat(indentLevel));
                } else if (c == '}' || c == ']') {
                    pretty.append('\n');
                    indentLevel--;
                    pretty.append("  ".repeat(indentLevel));
                    pretty.append(c);
                } else if (c == ',') {
                    pretty.append(c).append('\n');
                    pretty.append("  ".repeat(indentLevel));
                } else {
                    pretty.append(c);
                }
            } else {
                pretty.append(c);
            }
        }
        
        return pretty.toString();
    }
    
    /**
     * Check if a string is valid JSON (basic validation)
     * @param json the string to check
     * @return true if valid JSON, false otherwise
     */
    public static boolean isValidJson(String json) {
        if (json == null || json.trim().isEmpty()) {
            return false;
        }
        
        String trimmed = json.trim();
        
        // Basic validation - check if it starts and ends with proper brackets
        if (trimmed.startsWith("{") && trimmed.endsWith("}")) {
            return true; // Object
        }
        
        if (trimmed.startsWith("[") && trimmed.endsWith("]")) {
            return true; // Array
        }
        
        return false;
    }
}
