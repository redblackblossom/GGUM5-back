package com.catspot.service;

import com.catspot.dto.TimeTableGetResponse;
import com.catspot.util.TimeCalculator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TimeTableServiceTest {
    @Autowired
    private TimeTableService timeTableService;

    @BeforeEach
    public void 요일_설정() {
        TimeCalculator.setTestConditions("토", 4);
    }

    @AfterEach
    public void 테스트_초기화() {
        TimeCalculator.resetTestConditions();
    }

    @Test
    void 시간표출력_요일_교시_테스트() {
        String buildingName = "N";
        int classroomNumber = 113;

        TimeTableGetResponse response = timeTableService.getTimeTableList(buildingName, classroomNumber);

        assertEquals("N113", response.getClassName());
        System.out.println("response = " + response.getClassName());
    }
}
