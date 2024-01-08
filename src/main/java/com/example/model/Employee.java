package com.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "employee", schema = "JournalSystemDB")
public class Employee extends User{
    private Position position;
    public enum Position {DOCTOR, OTHER}

    public Employee() {
    }

    public Employee(String userName, String firstName, String lastName, LocalDate birthDate, Position position) {
        super(userName, firstName, lastName, birthDate, Role.EMPLOYEE);
        this.position = position;
    }
}
