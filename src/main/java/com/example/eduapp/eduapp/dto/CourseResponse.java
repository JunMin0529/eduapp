package com.example.eduapp.eduapp.dto;

import com.example.eduapp.eduapp.domain.Course;
import com.example.eduapp.eduapp.domain.Employee;
import lombok.Data;

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
}
