package com.dkit.oop.network;

import com.dkit.oop.controllers.StudentController;
import com.dkit.oop.models.Student;
import com.dkit.oop.utils.JsonUtils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Server class for handling student data requests over network
 * Demonstrates socket programming and concurrency with threads
 */
public class StudentServer {
    
    private final int port;
    private final StudentController studentController;
    private final ExecutorService threadPool;
    private ServerSocket serverSocket;
    private boolean running;
    
    public StudentServer(int port) {
        this.port = port;
        this.studentController = new StudentController();
        this.threadPool = Executors.newFixedThreadPool(10); // Thread pool for handling clients
        this.running = false;
    }
    
    /**
     * Start the server
     */
    public void start() {
        try {
            serverSocket = new ServerSocket(port);
            running = true;
            System.out.println("Student Server started on port " + port);
            
            while (running) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket.getInetAddress());
                
                // Handle each client in a separate thread
                threadPool.submit(new ClientHandler(clientSocket, studentController));
            }
        } catch (IOException e) {
            System.err.println("Error starting server: " + e.getMessage());
        } finally {
            stop();
        }
    }
    
    /**
     * Stop the server
     */
    public void stop() {
        running = false;
        if (serverSocket != null && !serverSocket.isClosed()) {
            try {
                serverSocket.close();
            } catch (IOException e) {
                System.err.println("Error closing server socket: " + e.getMessage());
            }
        }
        threadPool.shutdown();
        System.out.println("Server stopped");
    }
    
    /**
     * Client handler class for processing individual client requests
     */
    private static class ClientHandler implements Runnable {
        
        private final Socket clientSocket;
        private final StudentController studentController;
        
        public ClientHandler(Socket clientSocket, StudentController studentController) {
            this.clientSocket = clientSocket;
            this.studentController = studentController;
        }
        
        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
                
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    String response = processRequest(inputLine);
                    out.println(response);
                }
            } catch (IOException e) {
                System.err.println("Error handling client: " + e.getMessage());
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    System.err.println("Error closing client socket: " + e.getMessage());
                }
            }
        }
        
        /**
         * Process client requests
         * @param request the client request
         * @return response to send back to client
         */
        private String processRequest(String request) {
            try {
                String[] parts = request.split("\\|");
                if (parts.length == 0) {
                    return "ERROR|Invalid request format";
                }
                
                String command = parts[0].toUpperCase();
                
                switch (command) {
                    case "GET_ALL_STUDENTS":
                        return handleGetAllStudents();
                    case "GET_STUDENT_BY_ID":
                        return handleGetStudentById(parts);
                    case "GET_STUDENTS_BY_COURSE":
                        return handleGetStudentsByCourse(parts);
                    case "GET_STUDENTS_BY_YEAR":
                        return handleGetStudentsByYear(parts);
                    case "GET_HONORS_STUDENTS":
                        return handleGetHonorsStudents();
                    case "GET_STATISTICS":
                        return handleGetStatistics();
                    case "SEARCH_STUDENTS":
                        return handleSearchStudents(parts);
                    default:
                        return "ERROR|Unknown command: " + command;
                }
            } catch (Exception e) {
                return "ERROR|Server error: " + e.getMessage();
            }
        }
        
        private String handleGetAllStudents() {
            List<Student> students = studentController.getAllStudents();
            return "SUCCESS|" + JsonUtils.studentsToJson(students);
        }
        
        private String handleGetStudentById(String[] parts) {
            if (parts.length < 2) {
                return "ERROR|Missing student ID";
            }
            
            String studentId = parts[1];
            var student = studentController.findStudentById(studentId);
            
            if (student.isPresent()) {
                return "SUCCESS|" + JsonUtils.studentToJson(student.get());
            } else {
                return "ERROR|Student not found";
            }
        }
        
        private String handleGetStudentsByCourse(String[] parts) {
            if (parts.length < 2) {
                return "ERROR|Missing course name";
            }
            
            String course = parts[1];
            List<Student> students = studentController.getStudentsByCourse(course);
            return "SUCCESS|" + JsonUtils.studentsToJson(students);
        }
        
        private String handleGetStudentsByYear(String[] parts) {
            if (parts.length < 2) {
                return "ERROR|Missing year";
            }
            
            try {
                int year = Integer.parseInt(parts[1]);
                List<Student> students = studentController.getStudentsByYear(year);
                return "SUCCESS|" + JsonUtils.studentsToJson(students);
            } catch (NumberFormatException e) {
                return "ERROR|Invalid year format";
            }
        }
        
        private String handleGetHonorsStudents() {
            List<Student> students = studentController.getHonorsStudents();
            return "SUCCESS|" + JsonUtils.studentsToJson(students);
        }
        
        private String handleGetStatistics() {
            String stats = studentController.getStudentStatistics();
            return "SUCCESS|" + stats;
        }
        
        private String handleSearchStudents(String[] parts) {
            if (parts.length < 2) {
                return "ERROR|Missing search term";
            }
            
            String searchTerm = parts[1];
            List<Student> students = studentController.searchStudentsByName(searchTerm);
            return "SUCCESS|" + JsonUtils.studentsToJson(students);
        }
    }
    
    /**
     * Main method to start the server
     */
    public static void main(String[] args) {
        int port = 8080; // Default port
        if (args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.err.println("Invalid port number, using default: " + port);
            }
        }
        
        StudentServer server = new StudentServer(port);
        
        // Add shutdown hook to gracefully stop the server
        Runtime.getRuntime().addShutdownHook(new Thread(server::stop));
        
        server.start();
    }
}
