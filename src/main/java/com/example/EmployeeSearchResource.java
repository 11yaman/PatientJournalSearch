package com.example;

import com.example.dto.UserDto;
import com.example.mapping.StrategyMapper;
import com.example.model.Employee;
import com.example.model.User;
import com.example.repository.EmployeeRepository;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.hibernate.search.mapper.orm.session.SearchSession;

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
    @Inject
    SearchSession searchSession;

    @Transactional
    void onStart(@Observes StartupEvent ev) throws InterruptedException {
        if (employeeRepository.count() > 0) {
            System.out.println("FLAG");
            searchSession.massIndexer()
                    .startAndWait();
        }
    }

    @Path("/search")
    @GET
    @Transactional
    public List<UserDto> search(@QueryParam("q") String q,
                                @QueryParam("size") Optional<Integer> size) {
        List<Employee> result = searchSession.search(Employee.class)
                .where(f -> {
                    if (q == null || q.isBlank()) {
                        return f.matchAll();
                    } else {
                        return f.simpleQueryString()
                                .fields("firstName", "lastName")
                                .matching(q);
                    }
                }).sort(f -> f.field("firstName_sort").then().field("lastName_sort"))
                .fetchHits(size.orElse(20));

        return result.stream()
                .map(employee -> employeeMapper.map(employee))
                .collect(Collectors.toList());
    }
}