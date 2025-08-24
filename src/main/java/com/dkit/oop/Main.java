package com.dkit.oop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main application class for OOP Repeat Assessment Project
 * Demonstrates JavaFX application with proper OOP structure
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the main FXML file
        Parent root = FXMLLoader.load(getClass().getResource("/views/MainView.fxml"));
        
        // Set up the primary stage
        primaryStage.setTitle("OOP Repeat Assessment - Student Management System");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.setMinWidth(600);
        primaryStage.setMinHeight(400);
        
        // Show the application
        primaryStage.show();
    }

    public static void main(String[] args) {
        // Launch the JavaFX application
        launch(args);
    }
}
