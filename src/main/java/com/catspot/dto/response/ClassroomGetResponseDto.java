package com.catspot.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ClassroomGetResponseDto {
    private List<Integer> classrooms;
}
