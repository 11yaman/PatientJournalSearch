package com.example.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "patient", schema = "JournalSystemDB")
public class Patient extends User{

    @OneToMany(mappedBy = "sender", fetch = FetchType.LAZY)
    private List<Message> messages;

    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
    private List<Note> notes;

    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
    private List<Encounter> encounters;

    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
    @Column(name = "conditionEntities")
    private List<Condition> conditions;

    public Patient() {
    }

    public Patient(Long id, String username, String firstName, String lastName, LocalDate birthDate) {
        super(id, username, firstName, lastName, birthDate, Role.PATIENT);
    }

    public Patient(String username, String firstName, String lastName, LocalDate birthDate) {
        super(username, firstName, lastName, birthDate, Role.PATIENT);
    }

    public List<Message> getMessages() {
        return new ArrayList<>(messages);
    }

    public List<Note> getNotes() {
        return new ArrayList<>(notes);
    }

    public List<Encounter> getEncounters() {
        return new ArrayList<>(encounters);
    }

    public List<Condition> getConditions() {
        return new ArrayList<>(conditions);
    }
}
