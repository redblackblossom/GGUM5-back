package com.catspot.studyplace;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class StudyPlaceController {
    private final StudyPlaceRepository studyPlaceRepository;

    @GetMapping("/study-seat")
    public StudyPlaceResponse getAllStudyPlaces() {
        List<StudyPlace> allStudyPlaces = studyPlaceRepository.findAll();
        List<StudyPlaceDto> studyPlaceDtos = new ArrayList<>();

        for (StudyPlace studyPlace : allStudyPlaces) {
            StudyPlaceDto dto = StudyPlaceMapper.INSTANCE.studyPlaceToStudyPlaceDto(studyPlace);
            studyPlaceDtos.add(dto);
        }

        return StudyPlaceResponse.builder().data(studyPlaceDtos).build();
    }
}
