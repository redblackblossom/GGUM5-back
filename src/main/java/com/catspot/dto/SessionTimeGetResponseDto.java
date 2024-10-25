package com.catspot.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SessionTimeGetResponseDto {
    private String courseName;
    private String time;
}
