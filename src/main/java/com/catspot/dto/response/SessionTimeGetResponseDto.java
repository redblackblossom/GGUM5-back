package com.catspot.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SessionTimeGetResponseDto {
    private String time;
    private String subjectName;
}
