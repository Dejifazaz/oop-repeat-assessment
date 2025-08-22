-- Student Management System Database Schema
-- This file contains the SQL statements to create the database structure

-- Create the database (if using MySQL)
-- CREATE DATABASE IF NOT EXISTS student_management;
-- USE student_management;

-- Create the students table
CREATE TABLE IF NOT EXISTS students (
    student_id VARCHAR(20) PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    date_of_birth DATE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    course VARCHAR(100) NOT NULL,
    year_of_study INT NOT NULL CHECK (year_of_study >= 1 AND year_of_study <= 4),
    gpa DECIMAL(3,2) NOT NULL CHECK (gpa >= 0.0 AND gpa <= 4.0),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Create indexes for better performance
CREATE INDEX idx_students_course ON students(course);
CREATE INDEX idx_students_year ON students(year_of_study);
CREATE INDEX idx_students_gpa ON students(gpa);
CREATE INDEX idx_students_name ON students(last_name, first_name);

-- Insert sample data
INSERT INTO students (student_id, first_name, last_name, date_of_birth, email, course, year_of_study, gpa) VALUES
('S001', 'John', 'Doe', '2000-05-15', 'john.doe@email.com', 'Computer Science', 2, 3.80),
('S002', 'Jane', 'Smith', '1999-08-22', 'jane.smith@email.com', 'Computer Science', 3, 3.90),
('S003', 'Mike', 'Johnson', '2001-03-10', 'mike.johnson@email.com', 'Software Engineering', 1, 3.20),
('S004', 'Sarah', 'Wilson', '2000-12-05', 'sarah.wilson@email.com', 'Computer Science', 2, 3.70),
('S005', 'David', 'Brown', '1998-07-18', 'david.brown@email.com', 'Software Engineering', 4, 3.50),
('S006', 'Emily', 'Davis', '2002-01-30', 'emily.davis@email.com', 'Computer Science', 1, 3.60),
('S007', 'Michael', 'Wilson', '1999-11-12', 'michael.wilson@email.com', 'Software Engineering', 3, 3.40),
('S008', 'Lisa', 'Anderson', '2000-06-25', 'lisa.anderson@email.com', 'Computer Science', 2, 3.85),
('S009', 'James', 'Taylor', '2001-09-08', 'james.taylor@email.com', 'Software Engineering', 1, 3.30),
('S010', 'Amanda', 'Martinez', '1998-04-14', 'amanda.martinez@email.com', 'Computer Science', 4, 3.75);

-- Create a view for honors students
CREATE VIEW honors_students AS
SELECT * FROM students WHERE gpa >= 3.5;

-- Create a view for student statistics
CREATE VIEW student_statistics AS
SELECT 
    COUNT(*) as total_students,
    AVG(gpa) as average_gpa,
    COUNT(CASE WHEN gpa >= 3.5 THEN 1 END) as honors_count,
    COUNT(CASE WHEN gpa < 2.0 THEN 1 END) as warning_count
FROM students;

-- Create stored procedure to get students by course
DELIMITER //
CREATE PROCEDURE GetStudentsByCourse(IN course_name VARCHAR(100))
BEGIN
    SELECT * FROM students WHERE course = course_name ORDER BY last_name, first_name;
END //
DELIMITER ;

-- Create stored procedure to get students by year
DELIMITER //
CREATE PROCEDURE GetStudentsByYear(IN study_year INT)
BEGIN
    SELECT * FROM students WHERE year_of_study = study_year ORDER BY gpa DESC;
END //
DELIMITER ;

-- Create stored procedure to search students by name
DELIMITER //
CREATE PROCEDURE SearchStudentsByName(IN search_term VARCHAR(100))
BEGIN
    SELECT * FROM students 
    WHERE first_name LIKE CONCAT('%', search_term, '%') 
       OR last_name LIKE CONCAT('%', search_term, '%')
       OR CONCAT(first_name, ' ', last_name) LIKE CONCAT('%', search_term, '%')
    ORDER BY last_name, first_name;
END //
DELIMITER ;

-- Create trigger to update the updated_at timestamp
DELIMITER //
CREATE TRIGGER update_student_timestamp
BEFORE UPDATE ON students
FOR EACH ROW
BEGIN
    SET NEW.updated_at = CURRENT_TIMESTAMP;
END //
DELIMITER ;

-- Create trigger to validate email format
DELIMITER //
CREATE TRIGGER validate_email_format
BEFORE INSERT ON students
FOR EACH ROW
BEGIN
    IF NEW.email NOT REGEXP '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Invalid email format';
    END IF;
END //
DELIMITER ;

-- Grant permissions (adjust as needed for your database)
-- GRANT SELECT, INSERT, UPDATE, DELETE ON students TO 'student_user'@'localhost';
-- GRANT SELECT ON honors_students TO 'student_user'@'localhost';
-- GRANT SELECT ON student_statistics TO 'student_user'@'localhost';
-- GRANT EXECUTE ON PROCEDURE GetStudentsByCourse TO 'student_user'@'localhost';
-- GRANT EXECUTE ON PROCEDURE GetStudentsByYear TO 'student_user'@'localhost';
-- GRANT EXECUTE ON PROCEDURE SearchStudentsByName TO 'student_user'@'localhost';
