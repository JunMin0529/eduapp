package com.example.eduapp;

import com.example.eduapp.domain.Application;
import com.example.eduapp.domain.Employee;
import com.example.eduapp.dto.EmployeeRequest;
import com.example.eduapp.dto.EmployeeResponse;
import com.example.eduapp.exception.ex.ExceptionApi400;
import com.example.eduapp.repository.ApplicationRepository;
import com.example.eduapp.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ApplicationRepository applicationRepository;

    @Transactional
    public EmployeeResponse.SaveDTO save(EmployeeRequest.SaveDTO reqDTO) {

        Optional<Employee> employeeOptional = employeeRepository.findByEmail(reqDTO.getEmail());

        if (employeeOptional.isPresent()) {
            throw new ExceptionApi400("이미 사용 중인 이메일입니다.");
        }
        Employee savePS = employeeRepository.save(reqDTO.toEntity());

        return new EmployeeResponse.SaveDTO(savePS);
    }

    public List<EmployeeResponse.EmployeeApplicantDTO> getApplicants(Long id) {
        List<Application> applicationList = applicationRepository.findByEmployeeId(id);
        List<EmployeeResponse.EmployeeApplicantDTO> responseList = new ArrayList<>();
        for (Application application : applicationList) {
            responseList.add(new EmployeeResponse.EmployeeApplicantDTO(application));
        }

        return responseList;
    }
}
