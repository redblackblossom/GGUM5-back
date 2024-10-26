package com.catspot.crawler;

import com.catspot.studyplace.StudyPlace;
import com.catspot.studyplace.StudyPlaceRepository;
import com.catspot.studyplace.StudyPlaceResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.poi.ss.formula.functions.T;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CrawlerSchedulerTest {
    @InjectMocks
    private CrawlerScheduler crawlerScheduler;

    @Mock
    private LibraryCrawler libraryCrawler;

    @Mock
    private StudyPlaceRepository studyPlaceRepository;

    @Test
    void 스케줄러_정상동작() {
        Mockito.when(libraryCrawler.getData()).thenReturn(getTestPlace());
        Mockito.when(studyPlaceRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(getTestPlace().get(0)));
        Mockito.when(studyPlaceRepository.saveAll(ArgumentMatchers.anyList())).thenReturn(getTestPlace());

        crawlerScheduler.crawl();

        Mockito.verify(studyPlaceRepository, Mockito.times(1)).saveAll(ArgumentMatchers.anyList());
    }

    @Test
    void 스케줄러_예외동작() {
        Mockito.when(libraryCrawler.getData()).thenReturn(new ArrayList<>());
        Mockito.doNothing().when(studyPlaceRepository).deleteAll();

        crawlerScheduler.crawl();

        Mockito.verify(studyPlaceRepository, Mockito.times(1)).deleteAll();
    }

    private List<StudyPlace> getTestPlace() {
        List<StudyPlace> places = new ArrayList<>();
        places.add(StudyPlace.builder()
                .placeIdx(1L)
                .placeName("Hi")
                .url("test")
                .allSeats(10)
                .useSeats(5)
                .restSeats(5)
                .build());
        return places;
    }
}
