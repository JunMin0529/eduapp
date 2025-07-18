package com.example.eduapp.eduapp.repository;

import com.example.eduapp.eduapp.domain.Employee;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class EmployeeRepository {
    private final EntityManager em;

    public Employee save(Employee employee) {
        em.persist(employee);
        return (employee);
    }

    public Optional<Employee> findById(Long employeeId) {
        Employee employeePS = em.find(Employee.class, employeeId);
        return Optional.ofNullable(employeePS);
    }

    public Optional<Employee> findByEmail(String email) {
        try {
            Employee employee = em.createQuery("select e from Employee e where e.email = :email", Employee.class)
                    .setParameter("email", email)
                    .getSingleResult();
            return Optional.of(employee);
        } catch (Exception e) {
            return Optional.ofNullable(null);
        }
    }
}
