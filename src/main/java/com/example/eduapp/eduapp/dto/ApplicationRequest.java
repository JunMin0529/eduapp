package com.example.eduapp.eduapp.dto;

import com.example.eduapp.eduapp.domain.Application;
import com.example.eduapp.eduapp.domain.Course;
import com.example.eduapp.eduapp.domain.Employee;
import lombok.Data;

public class ApplicationRequest {

    @Data
    public static class SaveDTO {
        private Long employeeId;
        private Long courseId;

        public Application toEntity(Employee employee, Course course) {
            return Application.builder()
                    .employee(employee)
                    .course(course)
                    .build();
        }
    }
}
