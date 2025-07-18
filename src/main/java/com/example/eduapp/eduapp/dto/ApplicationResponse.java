package com.example.eduapp.eduapp.dto;

import com.example.eduapp.eduapp.domain.Course;
import com.example.eduapp.eduapp.domain.Employee;
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
