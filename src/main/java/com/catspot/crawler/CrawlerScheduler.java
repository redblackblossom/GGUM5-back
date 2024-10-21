package com.catspot.crawler;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class CrawlerScheduler {
    private final StudyPlaceRepository studyPlaceRepository;

    @Transactional
    @Scheduled(fixedRate = 60000)
    public void crawl() {
        List<StudyPlace> places = LibraryCrawler.getData();
        List<StudyPlace> studyPlacesToSave = new ArrayList<>();

        for (StudyPlace place : places) {
            StudyPlace studyPlace = studyPlaceRepository.findById(place.getPlaceIdx()).orElseGet(() -> place);

            studyPlace.setPlaceName(place.getPlaceName());
            studyPlace.setUrl(place.getUrl());
            studyPlace.setAllSeats(place.getAllSeats());
            studyPlace.setUseSeats(place.getUseSeats());
            studyPlace.setRestSeats(place.getRestSeats());

            studyPlacesToSave.add(studyPlace);
        }

        studyPlaceRepository.saveAll(studyPlacesToSave);
    }
}
