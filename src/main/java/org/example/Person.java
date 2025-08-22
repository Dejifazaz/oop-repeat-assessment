package org.example;

import java.io.Serializable;
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
    protected int age;
    protected String email;
    
    // Default constructor
    public Person() {
        this.firstName = "";
        this.lastName = "";
        this.age = 0;
        this.email = "";
    }
    
    // Parameterized constructor
    public Person(String firstName, String lastName, int age, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
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
    
    public int getAge() {
        return age;
    }
    
    public void setAge(int age) {
        this.age = age;
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
    
    public boolean isAdult() {
        return age >= 18;
    }
    
    // Abstract method that subclasses must implement
    public abstract String getDisplayName();
    
    // equals and hashCode methods
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Person person = (Person) obj;
        return age == person.age &&
               Objects.equals(firstName, person.firstName) &&
               Objects.equals(lastName, person.lastName) &&
               Objects.equals(email, person.email);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, age, email);
    }
    
    // toString method
    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                '}';
    }
}
