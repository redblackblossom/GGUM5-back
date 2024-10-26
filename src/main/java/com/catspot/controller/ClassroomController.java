package com.catspot.controller;

import com.catspot.dto.response.ClassroomGetResponseDto;
import com.catspot.dto.request.ClassroomRequestDto;
import com.catspot.service.ClassroomService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ClassroomController {
    private final ClassroomService classroomService;
    @PostMapping("/classroom")
    public ResponseEntity<ClassroomGetResponseDto> getClassroomList(@RequestBody ClassroomRequestDto classroomRequestDto) {
        return ResponseEntity.ok(classroomService.getClassroomList(classroomRequestDto.getBuildingName(), classroomRequestDto.getFloor(),classroomRequestDto.getDay(),classroomRequestDto.getHour()));
    }

}
