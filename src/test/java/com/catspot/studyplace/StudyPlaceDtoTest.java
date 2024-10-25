package com.catspot.studyplace;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StudyPlaceDtoTest {
    @Test
    public void DTO_생성_테스트() {
        StudyPlaceDto dto = StudyPlaceDto.builder()
                .placeIdx(1L)
                .placeName("1")
                .url("url")
                .allSeats(10)
                .useSeats(2)
                .restSeats(8)
                .build();

        Assertions.assertEquals(dto.getPlaceIdx(), 1);
    }
}
