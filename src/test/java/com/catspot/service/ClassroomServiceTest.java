package com.catspot.service;

import com.catspot.dto.ClassroomGetResponseDto;
import com.catspot.util.TimeCalculator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



@SpringBootTest
@Transactional
class ClassroomServiceTest {

    @Autowired
    private ClassroomService classroomService;

    @BeforeEach
    public void 요일_설정() {
        TimeCalculator.setTestConditions("화", 4);
    }

    @AfterEach
    public void 테스트_초기화() {
        TimeCalculator.resetTestConditions();
    }

    @Test
    public void 강의실_리스트_조회() {

        // given
        String buildingName = "D";
        int floor = 4;

        // when
        List<ClassroomGetResponseDto> classroomList = classroomService.getClassroomList(buildingName, floor);

        // then
        System.out.println("화요일 4교시 다솔관 4층 수업중인 강의실 리스트");
        for (ClassroomGetResponseDto classroomGetResponseDto : classroomList) {
            System.out.println("수업중인 강의실 = " + classroomGetResponseDto.getClassroomName());
        }
    }
}
