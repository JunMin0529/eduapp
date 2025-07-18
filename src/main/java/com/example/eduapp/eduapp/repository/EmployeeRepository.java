package com.example.eduapp.eduapp.repository;

import com.example.eduapp.eduapp.domain.Employee;
import com.example.eduapp.eduapp.dto.EmployeeRequest;
import com.example.eduapp.eduapp.dto.EmployeeResponse;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class EmployeeRepository {
    private final EntityManager em;

    public Employee save(Employee employee) {
        em.persist(employee);
        return (employee);
    }
}
