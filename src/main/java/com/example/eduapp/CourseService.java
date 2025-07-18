package com.example.eduapp;

import com.example.eduapp.domain.Application;
import com.example.eduapp.domain.Course;
import com.example.eduapp.dto.CourseRequest;
import com.example.eduapp.dto.CourseResponse;
import com.example.eduapp.exception.ex.ExceptionApi400;
import com.example.eduapp.repository.ApplicationRepository;
import com.example.eduapp.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final ApplicationRepository applicationRepository;

    @Transactional
    public CourseResponse.SaveDTO save(CourseRequest.SaveDTO reqDTO) {
        Optional<Course> courseOptional = courseRepository.findByTitle(reqDTO.getTitle());

        if (courseOptional.isPresent()) {
            throw new ExceptionApi400("이미 있는 교육입니다.");
        }

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
