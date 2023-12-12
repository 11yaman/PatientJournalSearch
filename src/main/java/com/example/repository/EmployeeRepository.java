package com.example.repository;

import com.example.model.Employee;
import com.example.model.Patient;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EmployeeRepository implements PanacheRepository<Employee> {
}