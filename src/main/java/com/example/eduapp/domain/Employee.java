package com.example.eduapp.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Table(name = "employee_tb")
@Entity
@Getter
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String department;

    @Column(unique = true)
    private String email;

    @Builder
    public Employee(Long id, String name, String department, String email) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.email = email;
    }

    // 기본 생성자
    protected Employee() {
    }
}
