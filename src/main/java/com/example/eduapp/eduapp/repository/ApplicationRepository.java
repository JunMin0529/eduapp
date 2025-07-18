package com.example.eduapp.eduapp.repository;

import com.example.eduapp.eduapp.domain.Application;
import com.example.eduapp.eduapp.domain.Course;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ApplicationRepository {
    private final EntityManager em;

    public Application save(Application application) {
        em.persist(application);
        return (application);
    }
}
