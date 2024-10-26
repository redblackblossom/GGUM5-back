package com.catspot.controller;

import com.catspot.dto.request.TimeTableRequestDto;
import com.catspot.dto.response.TimeTableGetResponse;
import com.catspot.dto.response.SessionTimeGetResponseDto;
import com.catspot.service.TimeTableService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TimeTableController.class)
class TimeTableControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TimeTableService timeTableService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getTimeTableListTest() throws Exception {
        // 요청 DTO 생성 및 필드 설정
        TimeTableRequestDto requestDto = new TimeTableRequestDto();
        ReflectionTestUtils.setField(requestDto, "buildingName", "Building A");
        ReflectionTestUtils.setField(requestDto, "classroomNumber", 101);
        ReflectionTestUtils.setField(requestDto, "day", "Monday");

        // SessionTimeGetResponseDto 리스트 생성
        List<SessionTimeGetResponseDto> schedule = Arrays.asList(
                SessionTimeGetResponseDto.builder()
                        .time("09:00-10:00")
                        .subjectName("Math")
                        .build(),
                SessionTimeGetResponseDto.builder()
                        .time("10:00-11:00")
                        .subjectName("Science")
                        .build()
        );

        // 응답 DTO 생성
        TimeTableGetResponse responseDto = new TimeTableGetResponse("Math Class", schedule);

        // Service의 동작을 Mocking하여 예상 응답 설정
        when(timeTableService.getTimeTableList(
                anyString(),
                anyInt(),
                anyString()
        )).thenReturn(responseDto);

        // MockMvc를 이용해 POST 요청 전송 및 결과 검증
        mockMvc.perform(post("/api/roomSchedule")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.className").value("Math Class"))
                .andExpect(jsonPath("$.schedule[0].time").value("09:00-10:00"))
                .andExpect(jsonPath("$.schedule[0].subjectName").value("Math"))
                .andExpect(jsonPath("$.schedule[1].time").value("10:00-11:00"))
                .andExpect(jsonPath("$.schedule[1].subjectName").value("Science"));

        // Service 호출 검증
        verify(timeTableService, times(1)).getTimeTableList(
                "Building A", 101, "Monday"
        );
    }
}
