package com.catspot.crawler;

import com.catspot.studyplace.StudyPlace;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class LibraryCrawlerTest {
    @InjectMocks
    private LibraryCrawler libraryCrawler;

    @Mock
    private JsoupHelper jsoupHelper;

    @Test
    void 크롤링_페이지_정상동작() throws IOException {
        // given
        Mockito.when(jsoupHelper.getDocument(ArgumentMatchers.anyString())).thenReturn(getTestDocu());

        // when
        List<StudyPlace> data = libraryCrawler.getData();

        // then
        System.out.println(data);
        Assertions.assertFalse(data.isEmpty());
    }

    @Test
    void 크롤링_예외처리() throws IOException {
        // given
        Mockito.when(jsoupHelper.getDocument(ArgumentMatchers.anyString())).thenThrow(new IOException());

        // when
        List<StudyPlace> data = libraryCrawler.getData();

        // then
        Assertions.assertTrue(data.isEmpty());
    }

    private Document getTestDocu() {
        String html = """
            <html>
                <tbody>
                <table>
                    <tr>
                        <td>
                            <a>test</a>
                        </td>
                        <td>
                            <b>1</b>
                        </td>
                        <td>
                            <b>1</b>
                        </td>
                        <td>
                            <b>1</b>
                        </td>
                        <td>
                            <b>1</b>
                        </td>
                        <td>
                            <b>1</b>
                        </td>
                        <td>
                            <b>1</b>
                        </td>
                        <td>
                            <b>1</b>
                        </td>
                        <td>
                            <b>1</b>
                        </td>
                    </tr>
                    </table>
                </tbody>
            </html>
        """;

        return Jsoup.parse(html);
    }
}
