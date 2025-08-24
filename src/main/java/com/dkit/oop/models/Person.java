package com.dkit.oop.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

/**
 * Base Person class demonstrating inheritance and abstract methods
 * This is the parent class for Student and other person types
 */
public abstract class Person implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    // Protected fields accessible to subclasses
    protected String firstName;
    protected String lastName;
    protected LocalDate dateOfBirth;
    protected String email;
    
    // Default constructor
    public Person() {
        this.firstName = "";
        this.lastName = "";
        this.dateOfBirth = LocalDate.now();
        this.email = "";
    }
    
    // Parameterized constructor
    public Person(String firstName, String lastName, LocalDate dateOfBirth, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
    }
    
    // Getters and Setters
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    // Business logic methods
    public String getFullName() {
        return firstName + " " + lastName;
    }
    
    public int getAge() {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }
    
    public boolean isAdult() {
        return getAge() >= 18;
    }
    
    // Abstract method that subclasses must implement
    public abstract String getDisplayName();
    
    // equals and hashCode methods
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Person person = (Person) obj;
        return Objects.equals(firstName, person.firstName) &&
               Objects.equals(lastName, person.lastName) &&
               Objects.equals(dateOfBirth, person.dateOfBirth) &&
               Objects.equals(email, person.email);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, dateOfBirth, email);
    }
    
    // toString method
    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", email='" + email + '\'' +
                '}';
    }
}
