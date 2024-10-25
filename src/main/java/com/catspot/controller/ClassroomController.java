package com.catspot.controller;

import com.catspot.dto.ClassroomGetResponseDto;
import com.catspot.service.ClassroomService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ClassroomController {
    private final ClassroomService classroomService;
    @GetMapping("/classroom")
    public ResponseEntity<List<ClassroomGetResponseDto>> getClassroomList(@RequestParam String buildingName,
                                                                         @RequestParam int floor) {
        return ResponseEntity.ok(classroomService.getClassroomList(buildingName, floor));
    }


}
