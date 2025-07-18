package com.example.eduapp.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Table(name = "application_tb")
@Entity
@Getter
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @CreationTimestamp
    private LocalDateTime appliedAt;

    @Builder
    public Application(Long id, Employee employee, Course course, LocalDateTime appliedAt) {
        this.id = id;
        this.employee = employee;
        this.course = course;
        this.appliedAt = appliedAt;
    }

    // 기본 생성자
    protected Application() {
    }
}
