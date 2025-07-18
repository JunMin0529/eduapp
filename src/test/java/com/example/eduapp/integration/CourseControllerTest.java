package com.example.eduapp.integration;

import com.example.eduapp.RestDoc;
import com.example.eduapp.dto.CourseRequest;
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
public class CourseControllerTest extends RestDoc {

    @Autowired
    private ObjectMapper om;

    @Test
    public void save_test() throws Exception {
        // given
        CourseRequest.SaveDTO reqDTO = new CourseRequest.SaveDTO();
        reqDTO.setTitle("Spring 기본");
        reqDTO.setDescription("Spring의 이론과 기초를 배워보아요");
        reqDTO.setCapacity(50);

        String requestBody = om.writeValueAsString(reqDTO);
//        System.out.println(requestBody);

        // when
        ResultActions actions = mvc.perform(
                MockMvcRequestBuilders
                        .post("/courses")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
        );

//         eye
        String responseBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println(responseBody);
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(200));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.msg").value("성공"));

        actions.andExpect(MockMvcResultMatchers.jsonPath("$.data.title").value("Spring 기본"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.data.description").value("Spring의 이론과 기초를 배워보아요"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.data.capacity").value(50));

        actions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }

    // 과정 이름 누락
    @Test
    public void save_title_null_test() throws Exception {
        // given
        CourseRequest.SaveDTO reqDTO = new CourseRequest.SaveDTO();
        reqDTO.setDescription("Spring의 이론과 기초를 배워보아요");
        reqDTO.setCapacity(50);

        String requestBody = om.writeValueAsString(reqDTO);
//        System.out.println(requestBody);

        // when
        ResultActions actions = mvc.perform(
                MockMvcRequestBuilders
                        .post("/courses")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
        );

//         eye
        String responseBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println(responseBody);
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(400));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.msg").value("title:과정 이름은 필수입니다."));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.data").value(nullValue()));

        actions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }

    // 과정 설명 누락
    @Test
    public void save_description_null_test() throws Exception {
        // given
        CourseRequest.SaveDTO reqDTO = new CourseRequest.SaveDTO();
        reqDTO.setTitle("Spring 기본");
        reqDTO.setCapacity(50);

        String requestBody = om.writeValueAsString(reqDTO);
//        System.out.println(requestBody);

        // when
        ResultActions actions = mvc.perform(
                MockMvcRequestBuilders
                        .post("/courses")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
        );

//         eye
        String responseBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println(responseBody);
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(400));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.msg").value("description:과정 설명은 필수입니다."));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.data").value(nullValue()));


        actions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }

    // 수강 정원 누락
    @Test
    public void save_capacity_null_test() throws Exception {
        // given
        CourseRequest.SaveDTO reqDTO = new CourseRequest.SaveDTO();
        reqDTO.setTitle("Spring 기본");
        reqDTO.setDescription("Spring의 이론과 기초를 배워보아요");

        String requestBody = om.writeValueAsString(reqDTO);
//        System.out.println(requestBody);

        // when
        ResultActions actions = mvc.perform(
                MockMvcRequestBuilders
                        .post("/courses")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
        );

//         eye
        String responseBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println(responseBody);
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(400));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.msg").value("capacity:수강 정원은 필수입니다."));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.data").value(nullValue()));


        actions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }

    @Test
    public void save_capacity_zero_test() throws Exception {
        // given
        CourseRequest.SaveDTO reqDTO = new CourseRequest.SaveDTO();
        reqDTO.setTitle("Spring 기본");
        reqDTO.setDescription("Spring의 이론과 기초를 배워보아요");
        reqDTO.setCapacity(0);

        String requestBody = om.writeValueAsString(reqDTO);
//        System.out.println(requestBody);

        // when
        ResultActions actions = mvc.perform(
                MockMvcRequestBuilders
                        .post("/courses")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
        );

//         eye
        String responseBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println(responseBody);
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(400));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.msg").value("capacity:최소 1명 이상의 수강 정원을 입력해주세요."));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.data").value(nullValue()));


        actions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }

    @Test
    public void get_applicants_test() throws Exception {
        // given
        Integer courseId = 1;

        ResultActions actions = mvc.perform(
                MockMvcRequestBuilders
                        .get("/courses/{id}/applicants", courseId)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // eye
        String responseBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println(responseBody);
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(200));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.msg").value("성공"));

        actions.andExpect(MockMvcResultMatchers.jsonPath("$.data[0].name").value("ssar"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.data[0].department").value("백엔드 개발자"));
        // 날짜는 패턴으로 검사
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.data[0].appliedAt")
                .value(matchesPattern("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d+")));

        actions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }

}
