package com.catspot.controller;

import com.catspot.dto.response.TimeTableGetResponse;
import com.catspot.dto.request.TimeTableRequestDto;
import com.catspot.service.TimeTableService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class TimeTableController {
    private final TimeTableService timeTableService;
    @PostMapping("/roomSchedule")
    public ResponseEntity<TimeTableGetResponse> getTimeTableList(@RequestBody TimeTableRequestDto timeTableRequestDto) {
        return ResponseEntity.ok(timeTableService.getTimeTableList(timeTableRequestDto.getBuildingName(), timeTableRequestDto.getClassroomNumber(),timeTableRequestDto.getDay()));
    }
}
