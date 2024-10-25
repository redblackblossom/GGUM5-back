package com.catspot.controller;

import com.catspot.dto.TimeTableGetResponse;
import com.catspot.service.TimeTableService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class TimeTableController {
    private final TimeTableService timeTableService;
    @GetMapping("/roomSchedule")
    public ResponseEntity<TimeTableGetResponse> getTimeTableList(@RequestParam String buildingName, @RequestParam int classroomNumber) {
        return ResponseEntity.ok(timeTableService.getTimeTableList(buildingName, classroomNumber));
    }
}
