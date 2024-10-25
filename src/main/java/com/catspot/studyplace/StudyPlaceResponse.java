package com.catspot.studyplace;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class StudyPlaceResponse {
    private List<StudyPlaceDto> data;
}
