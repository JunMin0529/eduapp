package com.example.eduapp.integration;

import com.example.eduapp.RestDoc;
import com.example.eduapp.dto.ApplicationRequest;
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

import static org.hamcrest.Matchers.nullValue;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class ApplicationControllerTest extends RestDoc {

    @Autowired
    private ObjectMapper om;

    @Test
    public void save_test() throws Exception {
        // given
        ApplicationRequest.SaveDTO reqDTO = new ApplicationRequest.SaveDTO();
        reqDTO.setCourseId(2L);
        reqDTO.setEmployeeId(2L);

        String requestBody = om.writeValueAsString(reqDTO);
//        System.out.println(requestBody);

        // when
        ResultActions actions = mvc.perform(
                MockMvcRequestBuilders
                        .post("/applications")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
        );

//         eye
        String responseBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println(responseBody);
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(200));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.msg").value("성공"));

        actions.andExpect(MockMvcResultMatchers.jsonPath("$.data.employee").value("cos"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.data.course").value("JPA 심화"));

        actions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }

    // 정원 초과로 인한 실퍠
    @Test
    public void save_full_test() throws Exception {
        // given
        ApplicationRequest.SaveDTO reqDTO = new ApplicationRequest.SaveDTO();
        reqDTO.setCourseId(1L);
        reqDTO.setEmployeeId(3L);

        String requestBody = om.writeValueAsString(reqDTO);
//        System.out.println(requestBody);

        // when
        ResultActions actions = mvc.perform(
                MockMvcRequestBuilders
                        .post("/applications")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
        );

//         eye
        String responseBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println(responseBody);
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(409));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.msg").value("정원이 가득 찼습니다."));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.data").value(nullValue()));

        actions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }

    // 중복 수강 신청
    @Test
    public void save_fail_test() throws Exception {
        // given
        ApplicationRequest.SaveDTO reqDTO = new ApplicationRequest.SaveDTO();
        reqDTO.setCourseId(2L);
        reqDTO.setEmployeeId(1L);

        String requestBody = om.writeValueAsString(reqDTO);
//        System.out.println(requestBody);

        // when
        ResultActions actions = mvc.perform(
                MockMvcRequestBuilders
                        .post("/applications")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
        );

//         eye
        String responseBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println(responseBody);
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(400));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.msg").value("이미 신청한 과목입니다."));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.data").value(nullValue()));

        actions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }


}
