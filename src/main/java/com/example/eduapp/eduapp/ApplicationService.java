package com.example.eduapp.eduapp;

import com.example.eduapp.eduapp.domain.Application;
import com.example.eduapp.eduapp.domain.Course;
import com.example.eduapp.eduapp.domain.Employee;
import com.example.eduapp.eduapp.dto.ApplicationRequest;
import com.example.eduapp.eduapp.dto.ApplicationResponse;
import com.example.eduapp.eduapp.exception.ex.ExceptionApi400;
import com.example.eduapp.eduapp.exception.ex.ExceptionApi409;
import com.example.eduapp.eduapp.repository.ApplicationRepository;
import com.example.eduapp.eduapp.repository.CourseRepository;
import com.example.eduapp.eduapp.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final EmployeeRepository employeeRepository;
    private final CourseRepository courseRepository;

    @Transactional
    public ApplicationResponse.SaveDTO save(ApplicationRequest.SaveDTO reqDTO) {
        Employee employeePS = employeeRepository.findById(reqDTO.getEmployeeId())
                .orElseThrow(() -> new ExceptionApi400("해당 직원을 찾을 수 없습니다."));

        Course coursePS = courseRepository.findById(reqDTO.getCourseId())
                .orElseThrow(() -> new ExceptionApi400("해당 강좌를 찾을 수 없습니다."));

        // 중복 신청 방지
        if (applicationRepository.existsByEmployeeIdAndCourseId(employeePS.getId(), coursePS.getId())) {
            throw new ExceptionApi400("이미 신청한 과목입니다.");
        }

        // 현재 신청자 수 조회
        long currentApplicants = applicationRepository.countByCourseId(coursePS.getId());

        if (coursePS.getCapacity() != null && currentApplicants >= coursePS.getCapacity()) {
            throw new ExceptionApi409("정원이 가득 찼습니다.");
        }

        Application savePS = applicationRepository.save(reqDTO.toEntity(employeePS, coursePS));

        return new ApplicationResponse.SaveDTO(savePS.getEmployee(), savePS.getCourse());
    }
}
