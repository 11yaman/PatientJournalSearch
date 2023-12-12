package com.example.mapping;

import com.example.dto.PatientDto;
import com.example.model.Patient;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class PatientMapper implements StrategyMapper<Patient, PatientDto> {

    @Override
    public PatientDto map(Patient source) {
        return new PatientDto(source.getId(), source.getUsername(),
                source.getFirstName(), source.getLastName(), source.getBirthDate());
    }

    @Override
    public List<PatientDto> mapAll(List<Patient> source) {
        List<PatientDto> patientDtos = new ArrayList<>();
        for (Patient p:
             source) {
            patientDtos.add(map(p));
        }
        return patientDtos;
    }
}
