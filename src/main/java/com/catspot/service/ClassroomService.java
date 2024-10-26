package com.catspot.service;

import com.catspot.dto.response.ClassroomGetResponseDto;
import com.catspot.entity.Classroom;
import com.catspot.exceptionhandler.CommonErrorCode;
import com.catspot.exceptionhandler.CustomException;
import com.catspot.repository.ClassroomRepository;
import com.catspot.util.TimeCalculator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class ClassroomService {
    private final ClassroomRepository classroomRepository;

    public ClassroomGetResponseDto getClassroomList(String buildingName, int floor, String day, int hour) {
        if (buildingName == null || buildingName.isEmpty() || floor < 0) {
            throw new CustomException(CommonErrorCode.INVALID_PARAMETER);
        }
        int currentTime = TimeCalculator.getCurrentTime(hour);
        List<Classroom> classroomList = findClassroomList(buildingName, floor, day, currentTime);
        return getClassroomGetResponseDtos(classroomList);
    }

    private List<Classroom> findClassroomList(String buildingName, int floor, String currentDay, int currentTime) {
        List<Classroom> classroomList = classroomRepository.findByBuildingNameAndFloorAndDayAndTime(buildingName, floor, currentDay, currentTime);
        return classroomList;
    }

    private ClassroomGetResponseDto getClassroomGetResponseDtos(List<Classroom> classroomList) {
        List<Integer> classroomNames = classroomList.stream()
                .map(classroom -> classroom.getClassroomNumber())
                .collect(Collectors.toList());
        return ClassroomGetResponseDto.builder()
                .classrooms(classroomNames)
                .build();
    }


}
