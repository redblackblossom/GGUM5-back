package com.catspot.dto.request;

import lombok.Getter;

@Getter
public class ClassroomRequestDto {
    private String buildingName;
    private int floor;
    private String day;
    private int hour;
}
