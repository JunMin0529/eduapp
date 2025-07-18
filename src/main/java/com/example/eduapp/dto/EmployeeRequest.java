package com.example.eduapp.dto;

import com.example.eduapp.domain.Employee;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

public class EmployeeRequest {

    @Data
    public static class SaveDTO {
        @NotBlank(message = "직원 이름은 필수입니다.")
        private String name;
        @NotBlank(message = "부서 이름은 필수입니다.")
        private String department;
        @NotBlank(message = "이메일은 필수입니다.")
        @Email
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
