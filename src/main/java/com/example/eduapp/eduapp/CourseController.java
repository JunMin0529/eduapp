package com.example.eduapp.eduapp;

import com.example.eduapp.eduapp._core.utils.Resp;
import com.example.eduapp.eduapp.dto.CourseRequest;
import com.example.eduapp.eduapp.dto.CourseResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @PostMapping("/courses")
    public ResponseEntity<?> save(@Valid @RequestBody CourseRequest.SaveDTO reqDTO, Errors errors) {
        CourseResponse.SaveDTO respDTO = courseService.save(reqDTO);
        return Resp.ok(respDTO);
    }

    @GetMapping("/courses/{id}/applicants")
    public ResponseEntity<?> getApplicants(@PathVariable Long id) {
        List<CourseResponse.CourseApplicantDTO> respDTO = courseService.getApplicants(id);
        return Resp.ok(respDTO);
    }
}
