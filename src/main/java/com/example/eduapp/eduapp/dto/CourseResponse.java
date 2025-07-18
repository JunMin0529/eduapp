package com.example.eduapp.eduapp.dto;

import com.example.eduapp.eduapp.domain.Application;
import com.example.eduapp.eduapp.domain.Course;
import lombok.Data;

import java.time.LocalDateTime;

public class CourseResponse {
    @Data
    public static class SaveDTO {
        private String title;
        private String description;
        private Integer capacity;

        public SaveDTO(Course course) {
            this.title = course.getTitle();
            this.description = course.getDescription();
            this.capacity = course.getCapacity();
        }
    }

    @Data
    public static class CourseApplicantDTO {
        private String name;
        private String department;
        private LocalDateTime appliedAt;

        public CourseApplicantDTO(Application application) {
            this.name = application.getEmployee().getName();
            this.department = application.getEmployee().getDepartment();
            this.appliedAt = application.getAppliedAt();
        }
    }
}
