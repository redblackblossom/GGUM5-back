package com.catspot.service;

import com.catspot.dto.ClassroomGetResponseDto;
import com.catspot.entity.Classroom;
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

    public List<ClassroomGetResponseDto> getClassroomList(String buildingName, int floor) {
        String currentDay = TimeCalculator.getCurrentDay();
        int currentTime = TimeCalculator.getCurrentTime();
        List<Classroom> classroomList = findClassroomList(buildingName, floor, currentDay, currentTime);
        return getClassroomGetResponseDtos(classroomList);
    }

    private List<Classroom> findClassroomList(String buildingName, int floor, String currentDay, int currentTime) {
        return classroomRepository.findByBuildingNameAndFloorAndDayAndTime(buildingName, floor, currentDay, currentTime);
    }

    private List<ClassroomGetResponseDto> getClassroomGetResponseDtos(List<Classroom> classroomList) {
        return classroomList.stream()
                .map(classroom -> ClassroomGetResponseDto.builder()
                        .classroomName(classroom.getBuildingName()+classroom.getClassroomNumber())
                        .build())
                .collect(Collectors.toList());
    }


}
