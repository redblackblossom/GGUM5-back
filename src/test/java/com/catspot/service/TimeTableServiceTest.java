package com.catspot.service;

import com.catspot.dto.response.TimeTableGetResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TimeTableServiceTest {
    @Autowired
    private TimeTableService timeTableService;

    @Test
    void 시간표출력_요일_교시_테스트() {
        String buildingName = "N";
        int classroomNumber = 113;
        String day = "토";

        TimeTableGetResponse response = timeTableService.getTimeTableList(buildingName, classroomNumber,day);

        assertEquals("N113", response.getClassName());
        System.out.println("response = " + response.getClassName());
    }
}
