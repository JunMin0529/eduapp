package com.example.eduapp.eduapp.dto;

import com.example.eduapp.eduapp.domain.Employee;
import lombok.Builder;
import lombok.Data;

public class EmployeeRequest {

    @Data
    public static class SaveDTO {
        private String name;
        private String department;
        private String email;


        public Employee toEntity() {
            return Employee.builder()
                    .name(name)
                    .department(department)
                    .email(email)
                    .build();
        }
    }
}
