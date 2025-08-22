package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * FileLoader class for loading student data from CSV files
 * Similar to the CA1 example provided
 */
public class FileLoader {
    
    /**
     * Load student data from a CSV file into an ArrayList
     * @param studentList - ArrayList to be filled with Student objects
     * @param fileName - name of CSV file containing student details
     */
    public static void loadStudentDataFromFile(ArrayList<Student> studentList, String fileName) {
        
        // Format of each row of data is:
        // StudentID,FirstName,LastName,Age,Email,Course,YearOfStudy,GPA
        // S001,John,Doe,20,john.doe@email.com,Computer Science,2,3.8
        
        try (Scanner sc = new Scanner(new File(fileName))
                .useDelimiter("[,\\r\\n]+")) {
            
            // Skip past the first line, as it has field names (not data)
            if (sc.hasNextLine()) {
                sc.nextLine();   // read the header line containing column titles, but don't use it
            }
            
            System.out.println("Loading student data from file: " + fileName);
            
            // while there is a next token to read....
            while (sc.hasNext()) {
                String studentId = sc.next();    // read student ID
                String firstName = sc.next();    // read first name
                String lastName = sc.next();     // read last name
                int age = sc.nextInt();          // read age
                String email = sc.next();        // read email
                String course = sc.next();       // read course
                int yearOfStudy = sc.nextInt();  // read year of study
                double gpa = sc.nextDouble();    // read GPA
                
                System.out.println(studentId + ", " + firstName + " " + lastName);
                
                studentList.add(new Student(studentId, firstName, lastName, age, 
                                          email, course, yearOfStudy, gpa));
            }
            
            System.out.println("Successfully loaded " + studentList.size() + " students from file.");
            
        } catch (FileNotFoundException exception) {
            System.out.println("FileNotFoundException caught. The file " + fileName + " may not exist." + exception);
        } catch (Exception exception) {
            System.out.println("Error reading file: " + exception.getMessage());
        }
    }
    
    /**
     * Create a sample CSV file with student data
     * @param fileName - name of the file to create
     */
    public static void createSampleCSVFile(String fileName) {
        try (java.io.PrintWriter writer = new java.io.PrintWriter(new java.io.FileWriter(fileName))) {
            
            // Write header
            writer.println("StudentID,FirstName,LastName,Age,Email,Course,YearOfStudy,GPA");
            
            // Write sample data
            writer.println("S001,John,Doe,20,john.doe@email.com,Computer Science,2,3.8");
            writer.println("S002,Jane,Smith,21,jane.smith@email.com,Computer Science,3,3.9");
            writer.println("S003,Mike,Johnson,19,mike.johnson@email.com,Software Engineering,1,3.2");
            writer.println("S004,Sarah,Wilson,20,sarah.wilson@email.com,Computer Science,2,3.7");
            writer.println("S005,David,Brown,22,david.brown@email.com,Software Engineering,4,3.5");
            writer.println("S006,Emily,Davis,18,emily.davis@email.com,Computer Science,1,3.6");
            writer.println("S007,Michael,Wilson,21,michael.wilson@email.com,Software Engineering,3,3.4");
            writer.println("S008,Lisa,Anderson,20,lisa.anderson@email.com,Computer Science,2,3.85");
            
            System.out.println("Sample CSV file created: " + fileName);
            
        } catch (java.io.IOException exception) {
            System.out.println("Error creating file: " + exception.getMessage());
        }
    }
    
    /**
     * Test the file loading functionality
     */
    public static void main(String[] args) {
        
        // Create a sample CSV file
        String fileName = "students-data.csv";
        createSampleCSVFile(fileName);
        
        // Load data from the file
        ArrayList<Student> studentList = new ArrayList<>();
        loadStudentDataFromFile(studentList, fileName);
        
        // Display loaded students
        System.out.println("\nLoaded students:");
        for (Student student : studentList) {
            System.out.println(student);
        }
    }
}
