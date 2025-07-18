package com.example.eduapp.eduapp.repository;

import com.example.eduapp.eduapp.domain.Application;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ApplicationRepository {
    private final EntityManager em;

    public Application save(Application application) {
        em.persist(application);
        return (application);
    }

    public List<Application> findByCourseId(Long courseId) {
        Query query = em.createQuery("select a from Application a join fetch a.course join fetch a.employee where a.course.id = :courseId", Application.class);
        query.setParameter("courseId", courseId);
        List<Application> courseList = query.getResultList();
        return courseList;
    }

    public List<Application> findByEmployeeId(Long employeeId) {
        Query query = em.createQuery("select a from Application a join fetch a.course join fetch a.employee where a.employee.id = :employeeId", Application.class);
        query.setParameter("employeeId", employeeId);
        List<Application> courseList = query.getResultList();
        return courseList;
    }

    // 참여 했는지 중복 체크
    public Boolean existsByEmployeeIdAndCourseId(Long employeeId, Long courseId) {
        Long count = em.createQuery("select count(a) from Application a where a.employee.id = :employeeId and a.course.id = :courseId", Long.class)
                .setParameter("employeeId", employeeId)
                .setParameter("courseId", courseId)
                .getSingleResult();
        return count > 0;
    }

    // 정원 체크
    public long countByCourseId(Long courseId) {
        return em.createQuery(
                        "select count(a) from Application a where a.course.id = :courseId", Long.class)
                .setParameter("courseId", courseId)
                .getSingleResult();
    }
}
