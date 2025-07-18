package com.example.eduapp.eduapp;

import com.example.eduapp.eduapp.domain.Course;
import com.example.eduapp.eduapp.domain.Employee;
import com.example.eduapp.eduapp.dto.CourseRequest;
import com.example.eduapp.eduapp.dto.CourseResponse;
import com.example.eduapp.eduapp.dto.EmployeeRequest;
import com.example.eduapp.eduapp.dto.EmployeeResponse;
import com.example.eduapp.eduapp.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;

    @Transactional
    public CourseResponse.SaveDTO save(CourseRequest.SaveDTO reqDTO) {
        Course savePS = courseRepository.save(reqDTO.toEntity());
        return new CourseResponse.SaveDTO(savePS);
    }
}
