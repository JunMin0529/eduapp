package com.example.eduapp.eduapp.integration;

import com.example.eduapp.eduapp.RestDoc;
import com.example.eduapp.eduapp.dto.EmployeeRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.matchesPattern;
import static org.hamcrest.Matchers.nullValue;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class EmployeeControllerTest extends RestDoc {

    @Autowired
    private ObjectMapper om;

    @Test
    public void save_test() throws Exception {
        // given
        EmployeeRequest.SaveDTO reqDTO = new EmployeeRequest.SaveDTO();
        reqDTO.setName("홍길동");
        reqDTO.setDepartment("개발과");
        reqDTO.setEmail("lsaoe157@gamil.com");

        String requestBody = om.writeValueAsString(reqDTO);
//        System.out.println(requestBody);

        // when
        ResultActions actions = mvc.perform(
                MockMvcRequestBuilders
                        .post("/employees")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
        );

//         eye
        String responseBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println(responseBody);
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(200));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.msg").value("성공"));

        actions.andExpect(MockMvcResultMatchers.jsonPath("$.data.name").value("홍길동"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.data.department").value("개발과"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.data.email").value("lsaoe157@gamil.com"));

        actions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }

    // 중복 이메일
    @Test
    public void save_fail_test() throws Exception {
        // given
        EmployeeRequest.SaveDTO reqDTO = new EmployeeRequest.SaveDTO();
        reqDTO.setName("ssar");
        reqDTO.setDepartment("백엔드 개발자");
        reqDTO.setEmail("hong@example.com");

        String requestBody = om.writeValueAsString(reqDTO);
//        System.out.println(requestBody);

        // when
        ResultActions actions = mvc.perform(
                MockMvcRequestBuilders
                        .post("/employees")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
        );

//         eye
        String responseBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println(responseBody);
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(400));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.msg").value("이미 사용 중인 이메일입니다."));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.data").value(nullValue()));

        actions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }

    // 이름 누락
    @Test
    public void save_name_null_fail_test() throws Exception {
        // given
        EmployeeRequest.SaveDTO reqDTO = new EmployeeRequest.SaveDTO();
        reqDTO.setDepartment("백엔드 개발자");
        reqDTO.setEmail("hong@example.com");

        String requestBody = om.writeValueAsString(reqDTO);
//        System.out.println(requestBody);

        // when
        ResultActions actions = mvc.perform(
                MockMvcRequestBuilders
                        .post("/employees")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
        );

//         eye
        String responseBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println(responseBody);
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(400));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.msg").value("name:직원 이름은 필수입니다."));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.data").value(nullValue()));

        actions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }

    // 부서 누락
    @Test
    public void save_department_null_fail_test() throws Exception {
        // given
        EmployeeRequest.SaveDTO reqDTO = new EmployeeRequest.SaveDTO();
        reqDTO.setName("편준민");
        reqDTO.setEmail("hong@example.com");

        String requestBody = om.writeValueAsString(reqDTO);
//        System.out.println(requestBody);

        // when
        ResultActions actions = mvc.perform(
                MockMvcRequestBuilders
                        .post("/employees")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
        );

//         eye
        String responseBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println(responseBody);
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(400));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.msg").value("department:부서 이름은 필수입니다."));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.data").value(nullValue()));

        actions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }

    // 이메일 누락
    @Test
    public void save_email_null_fail_test() throws Exception {
        // given
        EmployeeRequest.SaveDTO reqDTO = new EmployeeRequest.SaveDTO();
        reqDTO.setName("편준민");
        reqDTO.setDepartment("백엔드 개발자");

        String requestBody = om.writeValueAsString(reqDTO);
//        System.out.println(requestBody);

        // when
        ResultActions actions = mvc.perform(
                MockMvcRequestBuilders
                        .post("/employees")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
        );

//         eye
        String responseBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println(responseBody);
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(400));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.msg").value("email:이메일은 필수입니다."));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.data").value(nullValue()));

        actions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }


    @Test
    public void get_applicants_test() throws Exception {
        // given
        Integer employeeId = 1;

        ResultActions actions = mvc.perform(
                MockMvcRequestBuilders
                        .get("/employees/{id}/applications", employeeId)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // eye
        String responseBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println(responseBody);
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(200));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.msg").value("성공"));

        actions.andExpect(MockMvcResultMatchers.jsonPath("$.data[0].title").value("Spring Boot 입문"));
        // 날짜는 패턴으로 검사
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.data[0].appliedAt")
                .value(matchesPattern("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d+")));

        actions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }

}
