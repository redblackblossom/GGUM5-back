package com.catspot.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class TimeTableGetResponse {
    private String className;
    private List<SessionTimeGetResponseDto> schedule;
}
