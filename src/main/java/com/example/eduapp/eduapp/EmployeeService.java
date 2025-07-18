package com.example.eduapp.eduapp;

import com.example.eduapp.eduapp.domain.Employee;
import com.example.eduapp.eduapp.dto.EmployeeRequest;
import com.example.eduapp.eduapp.dto.EmployeeResponse;
import com.example.eduapp.eduapp.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Transactional
    public EmployeeResponse.SaveDTO save(EmployeeRequest.SaveDTO reqDTO) {
        Employee savePS = employeeRepository.save(reqDTO.toEntity());
        return new EmployeeResponse.SaveDTO(savePS);
    }
}
