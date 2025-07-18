package com.example.eduapp.eduapp.dto;

import com.example.eduapp.eduapp.domain.Application;
import com.example.eduapp.eduapp.domain.Course;
import com.example.eduapp.eduapp.domain.Employee;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

public class ApplicationRequest {

    @Data
    public static class SaveDTO {
        @NotNull(message = "신청자 ID는 필수입니다.")
        private Long employeeId;
        @NotNull(message = "신청할 과정 ID는 필수입니다.")
        private Long courseId;

        public Application toEntity(Employee employee, Course course) {
            return Application.builder()
                    .employee(employee)
                    .course(course)
                    .build();
        }
    }
}
