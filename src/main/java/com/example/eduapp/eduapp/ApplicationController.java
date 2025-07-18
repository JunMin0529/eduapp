package com.example.eduapp.eduapp;

import com.example.eduapp.eduapp._core.utils.Resp;
import com.example.eduapp.eduapp.dto.ApplicationRequest;
import com.example.eduapp.eduapp.dto.ApplicationResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class ApplicationController {
    private final ApplicationService applicationService;

    @PostMapping("/applications")
    public ResponseEntity<?> save(@Valid @RequestBody ApplicationRequest.SaveDTO reqDTO, Errors errors) {
        ApplicationResponse.SaveDTO respDTO = applicationService.save(reqDTO);
        return Resp.ok(respDTO);
    }
}
