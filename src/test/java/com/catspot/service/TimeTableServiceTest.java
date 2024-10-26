package com.catspot.service;

import com.catspot.dto.response.TimeTableGetResponse;
import com.catspot.exceptionhandler.CustomException;
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

    @Test
    void testGetTimeTableList_InvalidBuildingName() {
        String buildingName = "";
        int classroomNumber = 303;
        String day = "Monday";

        assertThrows(CustomException.class, () -> {
            timeTableService.getTimeTableList(buildingName, classroomNumber, day);
        });
    }

    @Test
    void testGetTimeTableList_InvalidClassroomNumber() {
        String buildingName = "A";
        int classroomNumber = -1; // 유효하지 않은 강의실 번호
        String day = "Monday";

        assertThrows(CustomException.class, () -> {
            timeTableService.getTimeTableList(buildingName, classroomNumber, day);
        });
    }
}
