package com.example.eduapp.eduapp.dto;

import com.example.eduapp.eduapp.domain.Application;
import com.example.eduapp.eduapp.domain.Employee;
import lombok.Data;

import java.time.LocalDateTime;

public class EmployeeResponse {
    @Data
    public static class SaveDTO {
        private String name;
        private String department;
        private String email;

        public SaveDTO(Employee employee) {
            this.name = employee.getName();
            this.department = employee.getDepartment();
            this.email = employee.getEmail();
        }
    }

    @Data
    public static class EmployeeApplicantDTO {
        private String title;
        private LocalDateTime appliedAt;

        public EmployeeApplicantDTO(Application application) {
            this.title = application.getCourse().getTitle();
            this.appliedAt = application.getAppliedAt();
        }
    }
}
