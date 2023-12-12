package com.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;

import java.time.LocalDate;

@Entity
@Indexed
@Table(name = "employee", schema = "JournalSystemDB")
public class Employee extends User{
    private Position position;
    public enum Position {DOCTOR, OTHER}

    public Employee() {
    }

    public Employee(String userName, String password, String firstName, String lastName, LocalDate birthDate, Position position) {
        super(userName, password, firstName, lastName, birthDate, Role.EMPLOYEE);
        this.position = position;
    }
}
