package com.catspot.service;

import com.catspot.dto.response.ClassroomGetResponseDto;
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
}
