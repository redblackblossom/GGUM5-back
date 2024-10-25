package com.catspot.studyplace;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StudyPlaceDto {
    private Long placeIdx;
    private String placeName;
    private String url;
    private Integer allSeats;
    private Integer useSeats;
    private Integer restSeats;
}
