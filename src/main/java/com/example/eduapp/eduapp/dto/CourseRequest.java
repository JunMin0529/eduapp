package com.example.eduapp.eduapp.dto;

import com.example.eduapp.eduapp.domain.Course;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

public class CourseRequest {

    @Data
    public static class SaveDTO {
        @NotBlank(message = "과정 이름은 필수입니다.")
        private String title;
        @NotBlank(message = "과정 설명은 필수입니다.")
        private String description;
        @Min(value = 1, message = "최소 1명 이상의 수강 정원을 입력해주세요.")
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
