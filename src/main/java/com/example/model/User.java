package com.example.model;

import jakarta.persistence.*;
import org.jboss.logging.annotations.Field;

import java.time.LocalDate;

@Entity
@Table(name = "user", schema = "JournalSystemDB")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String username;

    private LocalDate birthDate;
    private Role role;

    public enum Role {EMPLOYEE, PATIENT}

    public User() {
    }
    public User(Long id,
                String username,
                String firstName,
                String lastName,
                LocalDate birthDate,
                Role role) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.role = role;
    }

    public User(
            String username,
            String firstName,
            String lastName,
            LocalDate birthDate,
            Role role) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Role getRole() {
        return role;
    }

}
