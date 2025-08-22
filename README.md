# OOP Repeat Assessment Project

## Project Overview
This is the Object Oriented Programming repeat assessment project for DkIT, demonstrating core OOP concepts through a Student Management System.

## Project Structure
```
src/
├── main/
│   └── java/
│       └── org/
│           └── example/
│               ├── Main.java              # Main application entry point
│               ├── Person.java            # Abstract base class
│               ├── Student.java           # Student class extending Person
│               └── TestStudent.java       # Test class demonstrating functionality
├── resources/
│   └── database/
│       └── students.sql                   # Database schema
└── test/
    └── java/
        └── org/
            └── example/
```

## Requirements
- Java 11 or higher
- No external dependencies required

## Features Implemented
Based on OOP course requirements:
1. **Core OOP Concepts**: 
   - Encapsulation (private fields with getters/setters)
   - Inheritance (Student extends Person)
   - Polymorphism (abstract methods, method overriding)
   - Abstraction (abstract Person class)

2. **Interfaces**: 
   - Comparable (for sorting)
   - Serializable (for object persistence)

3. **Collections**: 
   - ArrayList usage
   - Sorting with Comparators
   - Stream operations

4. **Error Handling**: 
   - Exception handling with try-catch blocks
   - Input validation

## OOP Concepts Demonstrated

### Encapsulation
- Private instance variables in Student and Person classes
- Public getter and setter methods
- Data validation in setters

### Inheritance
- Student class extends Person class
- Inherits common properties (firstName, lastName, age, email)
- Overrides abstract methods from parent class

### Polymorphism
- Abstract `getDisplayName()` method in Person
- Different implementations in Student class
- Method overriding for toString(), equals(), hashCode()

### Interfaces
- Student implements Comparable<Student> for sorting
- Student implements Serializable for object persistence
- Custom sorting with lambda expressions

### Collections
- ArrayList<Student> for data storage
- Sorting with Comparator implementations
- Filtering and searching operations

## Build and Run
```bash
# Compile
javac -d build/classes src/main/java/org/example/*.java

# Run Main application
java -cp build/classes org.example.Main

# Run Test application
java -cp build/classes org.example.TestStudent
```

## Database Schema
The project includes a complete SQL database schema (`src/resources/database/students.sql`) with:
- Students table with proper constraints
- Indexes for performance
- Sample data
- Views and stored procedures
- Triggers for data validation

## Submission Checklist
- [x] Source files (.java) - Core OOP classes implemented
- [x] Database dump (.sql) - Complete schema provided
- [ ] Coversheet - To be completed
- [ ] GitHub repository link - To be created
- [ ] All files zipped together - To be done
- [ ] Due: August 31st, 2025 at 9:00 PM

## Testing
Run the test application to see all OOP concepts in action:
```bash
java -cp build/classes org.example.TestStudent
```

This will demonstrate:
- Object creation and initialization
- Inheritance and polymorphism
- Collections operations
- Sorting and filtering
- Error handling
- Statistics calculation
