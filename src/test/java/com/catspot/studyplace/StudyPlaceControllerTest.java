package com.catspot.studyplace;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(StudyPlaceController.class)
public class StudyPlaceControllerTest {
    @MockBean
    private StudyPlaceRepository studyPlaceRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void 열람실_데이터_조회() throws Exception {
        // given
        List<StudyPlace> response = studyPlaceResponse();
        doReturn(response).when(studyPlaceRepository).findAll();

        // then
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/study-seat"))
                        .andExpect(status().isOk());
    }

    private List<StudyPlace> studyPlaceResponse() {
        List<StudyPlace> response = new ArrayList<StudyPlace>();
        for (int i = 0; i < 5; i++) {
            StudyPlace studyPlace = StudyPlace.builder()
                    .placeIdx((long) i)
                    .placeName("열람실A")
                    .url("https://열람실A.com")
                    .allSeats(100)
                    .useSeats(10)
                    .restSeats(90)
                    .build();
            response.add(studyPlace);
        }
        return response;
    }
}
