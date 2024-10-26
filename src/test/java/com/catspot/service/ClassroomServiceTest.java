package com.catspot.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.catspot.dto.response.ClassroomGetResponseDto;
import com.catspot.exceptionhandler.CustomException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@Transactional
class ClassroomServiceTest {

    @Autowired
    private ClassroomService classroomService;

    @Test
    public void 강의실_리스트_조회() {

        // given
        String buildingName = "D";
        int floor = 4;
        String day = "화";
        int hour = 13;

        // when
        ClassroomGetResponseDto classroomList = classroomService.getClassroomList(buildingName, floor, day, hour);

        // then
        System.out.println("화요일 4교시 다솔관 4층 수업중인 강의실 리스트");
        for (int classroomNumber : classroomList.getClassrooms()) {
            System.out.println("수업중인 강의실 = " + classroomNumber);
        }
    }

    @Test
    void InvalidFloor() {
        String buildingName = "A";
        int floor = -1; // 유효하지 않은 층 수
        String day = "Monday";
        int hour = 10;

        assertThrows(CustomException.class, () -> {
            classroomService.getClassroomList(buildingName, floor, day, hour);
        });
    }
}
