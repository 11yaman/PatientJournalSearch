package com.example.model;

import jakarta.persistence.*;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;

import java.time.LocalDateTime;

@Entity
@Table(name = "conditions", schema = "JournalSystemDB")
public class Condition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @FullTextField(analyzer = "english")
    @Column(name = "conditionType")
    private String type;
    @FullTextField(analyzer = "english")
    private String description;
    private LocalDateTime startDate;
    @ManyToOne
    private Patient patient;

    public Condition() {
    }

    public Condition(String conditionType, String description, Patient patient) {
        this.type = conditionType;
        this.description = description;
        this.patient = patient;
        this.startDate = LocalDateTime.now();
    }

    public Condition(String conditionType, String description, LocalDateTime startDate, Patient patient) {
        this.type = conditionType;
        this.description = description;
        this.startDate = startDate;
        this.patient = patient;
    }

    public Long getId() {
        return id;
    }
    public String getConditionType() {
        return type;
    }
    public String getDescription() {
        return description;
    }
    public LocalDateTime getStartDate() {
        return startDate;
    }
    public Patient getPatient() {
        return patient;
    }
}