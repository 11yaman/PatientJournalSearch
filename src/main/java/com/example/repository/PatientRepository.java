package com.example.repository;

import com.example.model.Patient;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class PatientRepository implements PanacheRepository<Patient> {
}