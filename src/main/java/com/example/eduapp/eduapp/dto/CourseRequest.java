package com.example.eduapp.eduapp.dto;

import com.example.eduapp.eduapp.domain.Course;
import com.example.eduapp.eduapp.domain.Employee;
import lombok.Data;

public class CourseRequest {

    @Data
    public static class SaveDTO {
        private String title;
        private String description;
        private Integer capacity;

        public Course toEntity() {
            return Course.builder()
                    .title(title)
                    .description(description)
                    .capacity(capacity)
                    .build();
        }
    }
}
