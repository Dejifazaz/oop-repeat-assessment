#!/bin/bash

# OOP Repeat Assessment Project Build Script
# This script compiles and runs the Java application

echo "=== OOP Repeat Assessment Project Build Script ==="

# Create necessary directories
echo "Creating directories..."
mkdir -p build/classes
mkdir -p build/jar

# Set Java classpath
CLASSPATH="build/classes"

# Compile Java files
echo "Compiling Java files..."
find src/main/java -name "*.java" -print | xargs javac -d build/classes -cp "$CLASSPATH"

if [ $? -eq 0 ]; then
    echo "Compilation successful!"
else
    echo "Compilation failed!"
    exit 1
fi

# Create JAR file
echo "Creating JAR file..."
jar cfm build/jar/student-management.jar MANIFEST.MF -C build/classes .

if [ $? -eq 0 ]; then
    echo "JAR file created successfully!"
else
    echo "JAR creation failed!"
    exit 1
fi

# Run the application
echo "Running the application..."
java -cp build/classes com.dkit.oop.Main

echo "Build script completed!"
