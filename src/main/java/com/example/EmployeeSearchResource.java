package com.example;

import com.example.dto.UserDto;
import com.example.mapping.StrategyMapper;
import com.example.model.Employee;
import com.example.model.User;
import com.example.repository.EmployeeRepository;
import io.quarkus.runtime.StartupEvent;
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
import java.util.stream.Collectors;

@Path("/api/v1/employees")
@Produces(MediaType.APPLICATION_JSON)
public class EmployeeSearchResource {

    @Inject
    EmployeeRepository employeeRepository;
    @Inject
    StrategyMapper<User, UserDto> employeeMapper;

    @Path("/search")
    @GET
    @Transactional
    @RolesAllowed("EMPLOYEE")
    public List<UserDto> search(@QueryParam("q") String q,
                                @QueryParam("size") Optional<Integer> size) {
        if (q == null || q.trim().isEmpty()) {
            return Collections.emptyList();
        }

        String searchQuery = "%" + q.trim() + "%";
        List<Employee> employees = employeeRepository.find("LOWER(firstName) LIKE LOWER(?1) OR LOWER(lastName) LIKE LOWER(?1)",
                        searchQuery)
                .page(0, size.orElse(10))
                .list();

        return employeeMapper.mapAll(new ArrayList<>(employees));
    }
}