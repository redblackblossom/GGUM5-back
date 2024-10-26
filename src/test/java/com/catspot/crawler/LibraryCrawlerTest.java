package com.catspot.crawler;

import com.catspot.studyplace.StudyPlace;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;

class LibraryCrawlerTest {
    @Test
    void 크롤링_페이지_정상동작() {
        // when
        List<StudyPlace> data = LibraryCrawler.getData();

        // then
        System.out.println(data);
        Assertions.assertFalse(data.isEmpty());
    }

}
