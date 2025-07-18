package com.example.eduapp.eduapp;

import com.example.eduapp.eduapp.domain.Application;
import com.example.eduapp.eduapp.domain.Employee;
import com.example.eduapp.eduapp.dto.EmployeeRequest;
import com.example.eduapp.eduapp.dto.EmployeeResponse;
import com.example.eduapp.eduapp.repository.ApplicationRepository;
import com.example.eduapp.eduapp.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ApplicationRepository applicationRepository;

    @Transactional
    public EmployeeResponse.SaveDTO save(EmployeeRequest.SaveDTO reqDTO) {
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
