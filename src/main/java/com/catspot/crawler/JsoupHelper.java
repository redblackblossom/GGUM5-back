package com.catspot.crawler;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

@Component
public class JsoupHelper {
    Document getDocument(String url) throws IOException {
        return Jsoup.connect(url).get();
    }
}
