package com.example.eduapp.dto;

import com.example.eduapp.domain.Course;
import com.example.eduapp.domain.Employee;
import lombok.Data;

public class ApplicationResponse {
    @Data
    public static class SaveDTO {
        private String employee;
        private String course;

        public SaveDTO(Employee employee, Course course) {
            this.employee = employee.getName();
            this.course = course.getTitle();
        }
    }
}
