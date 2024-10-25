package com.catspot.studyplace;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StudyPlaceResponseTest {
    @Test
    void 열람실_응답클래스_생성() {
        List<StudyPlaceDto> studyPlaces = studyPlaceResponse();
        StudyPlaceResponse studyPlaceResponse = new StudyPlaceResponse(studyPlaces);

        assertFalse(studyPlaceResponse.getData().isEmpty());
    }

    private List<StudyPlaceDto> studyPlaceResponse() {
        List<StudyPlaceDto> response = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            StudyPlaceDto studyPlaceDto = StudyPlaceDto.builder()
                    .placeIdx((long) i)
                    .placeName("열람실A")
                    .url("https://열람실A.com")
                    .allSeats(100)
                    .useSeats(10)
                    .restSeats(90)
                    .build();

            response.add(studyPlaceDto);
        }
        return response;
    }
}
