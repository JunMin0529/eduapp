package com.example.eduapp.eduapp;

import com.example.eduapp.eduapp.domain.Application;
import com.example.eduapp.eduapp.domain.Course;
import com.example.eduapp.eduapp.domain.Employee;
import com.example.eduapp.eduapp.dto.CourseRequest;
import com.example.eduapp.eduapp.dto.CourseResponse;
import com.example.eduapp.eduapp.dto.EmployeeRequest;
import com.example.eduapp.eduapp.dto.EmployeeResponse;
import com.example.eduapp.eduapp.repository.ApplicationRepository;
import com.example.eduapp.eduapp.repository.CourseRepository;
import com.example.eduapp.eduapp.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final ApplicationRepository applicationRepository;

    @Transactional
    public CourseResponse.SaveDTO save(CourseRequest.SaveDTO reqDTO) {
        Course savePS = courseRepository.save(reqDTO.toEntity());
        return new CourseResponse.SaveDTO(savePS);
    }

    public List<CourseResponse.CourseApplicantDTO> getApplicants(Long id) {
        List<Application> applicationList = applicationRepository.findByCourseId(id);
        List<CourseResponse.CourseApplicantDTO> responseList = new ArrayList<>();
        for (Application application : applicationList) {
            responseList.add(new CourseResponse.CourseApplicantDTO(application));
        }

        return responseList;
    }
}
