package com.dkit.oop.network;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Client class for connecting to the Student Server
 * Demonstrates socket programming and client-server communication
 */
public class StudentClient {
    
    private final String host;
    private final int port;
    
    public StudentClient(String host, int port) {
        this.host = host;
        this.port = port;
    }
    
    /**
     * Send a request to the server and get response
     * @param request the request to send
     * @return the server response
     */
    public String sendRequest(String request) {
        try (Socket socket = new Socket(host, port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            
            out.println(request);
            return in.readLine();
            
        } catch (IOException e) {
            return "ERROR|Connection failed: " + e.getMessage();
        }
    }
    
    /**
     * Get all students from server
     * @return server response
     */
    public String getAllStudents() {
        return sendRequest("GET_ALL_STUDENTS");
    }
    
    /**
     * Get a student by ID from server
     * @param studentId the student ID
     * @return server response
     */
    public String getStudentById(String studentId) {
        return sendRequest("GET_STUDENT_BY_ID|" + studentId);
    }
    
    /**
     * Get students by course from server
     * @param course the course name
     * @return server response
     */
    public String getStudentsByCourse(String course) {
        return sendRequest("GET_STUDENTS_BY_COURSE|" + course);
    }
    
    /**
     * Get students by year from server
     * @param year the year of study
     * @return server response
     */
    public String getStudentsByYear(int year) {
        return sendRequest("GET_STUDENTS_BY_YEAR|" + year);
    }
    
    /**
     * Get honors students from server
     * @return server response
     */
    public String getHonorsStudents() {
        return sendRequest("GET_HONORS_STUDENTS");
    }
    
    /**
     * Get statistics from server
     * @return server response
     */
    public String getStatistics() {
        return sendRequest("GET_STATISTICS");
    }
    
    /**
     * Search students by name from server
     * @param searchTerm the search term
     * @return server response
     */
    public String searchStudents(String searchTerm) {
        return sendRequest("SEARCH_STUDENTS|" + searchTerm);
    }
    
    /**
     * Interactive client application
     */
    public void runInteractive() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Student Client - Interactive Mode");
        System.out.println("Commands:");
        System.out.println("  all - Get all students");
        System.out.println("  id <studentId> - Get student by ID");
        System.out.println("  course <courseName> - Get students by course");
        System.out.println("  year <year> - Get students by year");
        System.out.println("  honors - Get honors students");
        System.out.println("  stats - Get statistics");
        System.out.println("  search <term> - Search students by name");
        System.out.println("  quit - Exit");
        System.out.println();
        
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            
            if (input.equalsIgnoreCase("quit")) {
                break;
            }
            
            String[] parts = input.split("\\s+", 2);
            String command = parts[0].toLowerCase();
            String response;
            
            switch (command) {
                case "all":
                    response = getAllStudents();
                    break;
                case "id":
                    if (parts.length < 2) {
                        System.out.println("Usage: id <studentId>");
                        continue;
                    }
                    response = getStudentById(parts[1]);
                    break;
                case "course":
                    if (parts.length < 2) {
                        System.out.println("Usage: course <courseName>");
                        continue;
                    }
                    response = getStudentsByCourse(parts[1]);
                    break;
                case "year":
                    if (parts.length < 2) {
                        System.out.println("Usage: year <year>");
                        continue;
                    }
                    try {
                        int year = Integer.parseInt(parts[1]);
                        response = getStudentsByYear(year);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid year format");
                        continue;
                    }
                    break;
                case "honors":
                    response = getHonorsStudents();
                    break;
                case "stats":
                    response = getStatistics();
                    break;
                case "search":
                    if (parts.length < 2) {
                        System.out.println("Usage: search <term>");
                        continue;
                    }
                    response = searchStudents(parts[1]);
                    break;
                default:
                    System.out.println("Unknown command: " + command);
                    continue;
            }
            
            System.out.println("Response: " + response);
            System.out.println();
        }
        
        scanner.close();
        System.out.println("Client disconnected");
    }
    
    /**
     * Main method to run the client
     */
    public static void main(String[] args) {
        String host = "localhost";
        int port = 8080;
        
        if (args.length >= 1) {
            host = args[0];
        }
        if (args.length >= 2) {
            try {
                port = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                System.err.println("Invalid port number, using default: " + port);
            }
        }
        
        StudentClient client = new StudentClient(host, port);
        client.runInteractive();
    }
}
