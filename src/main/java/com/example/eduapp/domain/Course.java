package com.example.eduapp.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Table(name = "course_tb")
@Entity
@Getter
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private Integer capacity;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Builder
    public Course(Long id, String title, String description, Integer capacity, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.createdAt = createdAt;
    }


    // 기본 생성자
    protected Course() {
    }
}
