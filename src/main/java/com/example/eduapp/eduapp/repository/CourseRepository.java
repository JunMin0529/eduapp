package com.example.eduapp.eduapp.repository;

import com.example.eduapp.eduapp.domain.Course;
import com.example.eduapp.eduapp.domain.Employee;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CourseRepository {
    private final EntityManager em;

    public Course save(Course course) {
        em.persist(course);
        return (course);
    }

    public Optional<Course> findById(Long courseId) {
        Course coursePS = em.find(Course.class, courseId);
        return Optional.ofNullable(coursePS);
    }
}
