package com.example;

import com.example.dto.PatientDto;
import com.example.mapping.StrategyMapper;
import com.example.model.Employee;
import com.example.model.Patient;
import com.example.repository.PatientRepository;
import io.quarkus.runtime.StartupEvent;
import io.quarkus.security.Authenticated;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.RestQuery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Path("/api/v1/patients")
@Produces(MediaType.APPLICATION_JSON)
public class PatientSearchResource {
    @Inject
    PatientRepository patientRepository;
    @Inject
    StrategyMapper<Patient, PatientDto> patientMapper;

    @Path("/search")
    @GET
    @Transactional
    @RolesAllowed("EMPLOYEE")
    public List<PatientDto> search(@QueryParam("q") String q,
                                @QueryParam("size") Optional<Integer> size) {
        if (q == null || q.trim().isEmpty()) {
            return Collections.emptyList();
        }

        String searchQuery = "%" + q.trim() + "%";
        List<Patient> patients = patientRepository.find("LOWER(firstName) LIKE LOWER(?1) OR LOWER(lastName) LIKE LOWER(?1)",
                        searchQuery)
                .page(0, size.orElse(10))
                .list();

        return patientMapper.mapAll(new ArrayList<>(patients));
    }
}