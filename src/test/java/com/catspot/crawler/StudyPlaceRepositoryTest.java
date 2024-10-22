package com.catspot.crawler;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class StudyPlaceRepositoryTest {

    @Autowired
    private StudyPlaceRepository studyPlaceRepository;

    @Test
    void 저장_후_인덱스를_통한_조회() {
        StudyPlace studyPlace = StudyPlace.builder()
                .placeIdx(1L)
                .placeName("1")
                .url("url")
                .allSeats(10)
                .useSeats(2)
                .restSeats(8)
                .build();
        studyPlaceRepository.save(studyPlace);

        StudyPlace foundStudyPlace = studyPlaceRepository.findById(1L).get();

        Assertions.assertNotNull(foundStudyPlace);
        Assertions.assertEquals(foundStudyPlace.getPlaceIdx(), 1);
        Assertions.assertEquals(foundStudyPlace.getPlaceName(), "1");
    }
}
