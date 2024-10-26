package com.catspot.service;

import com.catspot.dto.response.SessionTimeGetResponseDto;
import com.catspot.dto.response.TimeTableGetResponse;
import com.catspot.entity.TimeTable;
import com.catspot.exceptionhandler.CommonErrorCode;
import com.catspot.exceptionhandler.CustomException;
import com.catspot.repository.TimeTableRepository;
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

    public TimeTableGetResponse getTimeTableList(String buildingName, int classroomNumber, String day) {
        if (buildingName == null || buildingName.isEmpty() || classroomNumber < 0) {
            throw new CustomException(CommonErrorCode.INVALID_PARAMETER);
        }
        List<TimeTable> timeTableList = findTimeTableList(buildingName, classroomNumber, day);
        List<SessionTimeGetResponseDto> schedules = getSessionTimeGetResponseDtos(timeTableList);
        String className = buildingName+classroomNumber;
        return new TimeTableGetResponse(className,schedules);
    }

    private List<TimeTable> findTimeTableList(String buildingName, int classroomNumber, String currentDay) {
        List<TimeTable> timeTableList = timeTableRepository.findByClassroomName(buildingName, classroomNumber, currentDay);
        return timeTableList;
    }

    private List<SessionTimeGetResponseDto> getSessionTimeGetResponseDtos(List<TimeTable> timeTableList) {
        List<SessionTimeGetResponseDto> schedules = timeTableList.stream()
                .map(timeTable -> SessionTimeGetResponseDto.builder()
                        .subjectName(timeTable.getCourseName())
                        .time(timeTable.getSessionTime().getTime())
                        .build()).collect(Collectors.toList());
        return schedules;
    }
}
