package com.example;

import com.example.dto.PatientDto;
import com.example.mapping.StrategyMapper;
import com.example.model.Patient;
import com.example.repository.PatientRepository;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.hibernate.search.mapper.orm.session.SearchSession;

import java.util.List;
import java.util.Optional;

@Path("/api/v1/patients")
@Produces(MediaType.APPLICATION_JSON)
public class PatientSearchResource {
    @Inject
    PatientRepository patientRepository;
    @Inject
    StrategyMapper<Patient, PatientDto> patientMapper;
    @Inject
    SearchSession searchSession;

    @Transactional
    void onStart(@Observes StartupEvent ev) throws InterruptedException {
        if (patientRepository.count() > 0) {
            System.out.println("FLAG");
            searchSession.massIndexer()
                    .startAndWait();
        }
    }

    @Path("/search")
    @GET
    @Transactional
    public List<PatientDto> search(@QueryParam("q") String q,
                                   @QueryParam("size") Optional<Integer> size) {
        List<Patient> result = searchSession.search(Patient.class)
                .where(f -> {
                    if (q == null || q.isBlank()) {
                        return f.matchAll();
                    } else {
                        return f.simpleQueryString()
                                .fields("firstName", "lastName",
                                        "conditions.type", "conditions.description")
                                .matching(q);
                    }
                }).sort(f -> f.field("firstName_sort").then().field("lastName_sort"))
                .fetchHits(size.orElse(20));
        return patientMapper.mapAll(result);
    }
}