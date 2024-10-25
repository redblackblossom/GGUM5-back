package com.catspot.service;

import com.catspot.dto.SessionTimeGetResponseDto;
import com.catspot.dto.TimeTableGetResponse;
import com.catspot.entity.TimeTable;
import com.catspot.repository.TimeTableRepository;
import com.catspot.util.TimeCalculator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class TimeTableService {
    private final TimeTableRepository timeTableRepository;

    public TimeTableGetResponse getTimeTableList(String buildingName, int classroomNumber) {
        String currentDay = TimeCalculator.getCurrentDay();
        List<TimeTable> timeTableList = timeTableRepository.findByClassroomName(buildingName, classroomNumber,currentDay);
        List<SessionTimeGetResponseDto> schedules = getSessionTimeGetResponseDtos(timeTableList);
        String className = buildingName+classroomNumber;
        return new TimeTableGetResponse(className,schedules);
    }

    private List<SessionTimeGetResponseDto> getSessionTimeGetResponseDtos(List<TimeTable> timeTableList) {
        List<SessionTimeGetResponseDto> schedules = timeTableList.stream()
                .map(timeTable -> SessionTimeGetResponseDto.builder()
                        .courseName(timeTable.getCourseName())
                        .time(timeTable.getSessionTime().getTime())
                        .build()).collect(Collectors.toList());
        return schedules;
    }
}
