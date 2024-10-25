package com.catspot.studyplace;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class StudyPlaceMapperTest {
    @Test
    public void DTO_매핑_테스트() {
        StudyPlace studyPlace = getStudyPlace();

        StudyPlaceDto studyPlaceDto = StudyPlaceMapper.INSTANCE.studyPlaceToStudyPlaceDto(studyPlace);

        assertNotNull(studyPlaceDto);
        assertEquals(studyPlaceDto.getPlaceIdx(), studyPlace.getPlaceIdx());
        assertEquals(studyPlaceDto.getPlaceName(), studyPlace.getPlaceName());
        assertEquals(studyPlaceDto.getUrl(), studyPlace.getUrl());
        assertEquals(studyPlaceDto.getAllSeats(), studyPlace.getAllSeats());
        assertEquals(studyPlaceDto.getUseSeats(), studyPlace.getUseSeats());
        assertEquals(studyPlaceDto.getRestSeats(), studyPlace.getRestSeats());
    }

    @Test
    public void DTO_null_매핑_테스트() {
        StudyPlace studyPlace = null;

        StudyPlaceDto studyPlaceDto = StudyPlaceMapper.INSTANCE.studyPlaceToStudyPlaceDto(studyPlace);

        assertNull(studyPlaceDto);
    }

    private StudyPlace getStudyPlace() {
        StudyPlace studyPlace = StudyPlace.builder()
                .placeIdx(1L)
                .placeName("1")
                .url("url")
                .allSeats(10)
                .useSeats(2)
                .restSeats(8)
                .build();
        return studyPlace;
    }
}
