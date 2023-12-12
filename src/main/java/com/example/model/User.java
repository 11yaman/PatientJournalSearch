package com.example.model;

import jakarta.persistence.*;
import org.hibernate.search.engine.backend.types.Sortable;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.IndexedEmbedded;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.KeywordField;
import org.jboss.logging.annotations.Field;

import java.time.LocalDate;

@Entity
@Table(name = "user", schema = "JournalSystemDB")
@Inheritance(strategy = InheritanceType.JOINED)
@Indexed
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @FullTextField(analyzer = "name")
    @KeywordField(name = "firstName_sort", sortable = Sortable.YES, normalizer = "sort")
    private String firstName;
    @FullTextField(analyzer = "name")
    @KeywordField(name = "lastName_sort", sortable = Sortable.YES, normalizer = "sort")
    private String lastName;
    @FullTextField(analyzer = "name")
    @Column(unique = true)
    private String username;
    private String password;

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
            String password,
            String firstName,
            String lastName,
            LocalDate birthDate,
            Role role) {
        this.username = username;
        this.password = password;
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
