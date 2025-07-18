package com.example.eduapp.eduapp.dto;

import com.example.eduapp.eduapp.domain.Employee;
import lombok.Data;

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
}
