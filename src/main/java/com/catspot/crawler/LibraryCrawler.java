package com.catspot.crawler;

import com.catspot.exceptionhandler.CommonErrorCode;
import com.catspot.exceptionhandler.CustomException;
import com.catspot.exceptionhandler.ErrorCode;
import com.catspot.studyplace.StudyPlace;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class LibraryCrawler {
    private static final String baseUrl = "http://203.229.203.240/8080";
    private static final String tableUrl = "/Domian5_jythh.asp";
    private static final String seatUrl = "/roomview5.asp?room_no=";

    public static List<StudyPlace> getData() {
        List<StudyPlace> data = new ArrayList<>();
        try {
            Document table = Jsoup.connect(baseUrl + tableUrl).get();
            Elements rows = table.select("tbody tr");

            for (int i = 0; i < rows.size(); i ++) {
                Element row = rows.get(i);
                String name = row.select("a").text();
                String url = baseUrl + seatUrl + (i + 1);

                Document seat = Jsoup.connect(url).get();
                Elements seatStatus = seat.select("td b");

                Integer allSeats = Integer.parseInt(seatStatus.get(3).text());
                Integer useSeats = Integer.parseInt(seatStatus.get(5).text());
                Integer restSeats = Integer.parseInt(seatStatus.get(7).text());

                StudyPlace studyPlace = StudyPlace.builder()
                        .placeIdx((long) i)
                        .placeName(name)
                        .url(url)
                        .allSeats(allSeats)
                        .useSeats(useSeats)
                        .restSeats(restSeats)
                        .build();

                data.add(studyPlace);
            }


        } catch (Exception e) {
            log.error("크롤링에 실패했습니다.");
            data = new ArrayList<>();
        }
        return data;
    }
}
