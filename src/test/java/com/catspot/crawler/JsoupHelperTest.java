package com.catspot.crawler;

import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class JsoupHelperTest {
    @Test
    void Jsoup_테스트() {
        JsoupHelper jsoupHelper = new JsoupHelper();

        Assertions.assertDoesNotThrow(() -> jsoupHelper.getDocument("https://google.com"));
    }
}
