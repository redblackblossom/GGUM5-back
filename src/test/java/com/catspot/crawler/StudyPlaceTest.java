package com.catspot.crawler;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StudyPlaceTest {
    @Test
    void 열람실_엔티티_생성() {
        StudyPlace studyPlace = StudyPlace.builder()
                .placeIdx(1L)
                .placeName("1")
                .url("url")
                .allSeats(10)
                .useSeats(2)
                .restSeats(8)
                .build();

        Assertions.assertEquals(studyPlace.getPlaceIdx(), 1);
    }
}
