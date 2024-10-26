package com.catspot.controller;

import com.catspot.controller.ClassroomController;
import com.catspot.dto.request.ClassroomRequestDto;
import com.catspot.dto.response.ClassroomGetResponseDto;
import com.catspot.service.ClassroomService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

@WebMvcTest(ClassroomController.class)
public class ClassroomControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClassroomService classroomService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getClassroomListTest() throws Exception {
        // 요청 DTO 생성 및 필드 설정
        ClassroomRequestDto requestDto = new ClassroomRequestDto();
        ReflectionTestUtils.setField(requestDto, "buildingName", "Building A");
        ReflectionTestUtils.setField(requestDto, "floor", 2);
        ReflectionTestUtils.setField(requestDto, "day", "Monday");
        ReflectionTestUtils.setField(requestDto, "hour", 10);

        // 응답 DTO 생성 및 필드 설정
        List<Integer> classrooms = Arrays.asList(101, 102, 103);
        ClassroomGetResponseDto responseDto = ClassroomGetResponseDto.builder()
                .classrooms(classrooms)
                .build();

        // Service의 동작을 Mocking하여 예상 응답 설정
        when(classroomService.getClassroomList(
                anyString(),
                anyInt(),
                anyString(),
                anyInt()
        )).thenReturn(responseDto);

        // MockMvc를 이용해 POST 요청 전송 및 결과 검증
        mockMvc.perform(post("/api/classroom")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.classrooms[0]").value(101))
                .andExpect(jsonPath("$.classrooms[1]").value(102))
                .andExpect(jsonPath("$.classrooms[2]").value(103));

        // Service 호출 검증
        verify(classroomService, times(1)).getClassroomList(
                "Building A", 2, "Monday", 10
        );
    }
}
