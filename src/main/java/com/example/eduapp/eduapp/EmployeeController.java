package com.example.eduapp.eduapp;

import com.example.eduapp.eduapp._core.utils.Resp;
import com.example.eduapp.eduapp.dto.EmployeeRequest;
import com.example.eduapp.eduapp.dto.EmployeeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping("/employees")
    public ResponseEntity<?> save(@RequestBody EmployeeRequest.SaveDTO reqDTO) {
        EmployeeResponse.SaveDTO respDTO = employeeService.save(reqDTO);
        return Resp.ok(respDTO);
    }

    @GetMapping("/employees/{id}/applications")
    public ResponseEntity<?> getApplicants(@PathVariable("id") Long id) {
        List<EmployeeResponse.EmployeeApplicantDTO> respDTO = employeeService.getApplicants(id);
        return Resp.ok(respDTO);
    }
}
