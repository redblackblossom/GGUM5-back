package com.catspot.dto.request;

import lombok.Getter;

@Getter
public class TimeTableRequestDto {
    private String buildingName;
    private int classroomNumber;
    private String day;
    private int hour;
}
