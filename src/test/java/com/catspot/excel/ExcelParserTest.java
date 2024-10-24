package com.catspot.excel;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ExcelParserTest {

    @Test
    public void parserTest(){
        ExcelParser ep = new ExcelParser();
        List<Schedule> ret = ep.parse("수1(N411), 금1~2(N411)");
        for (Schedule r : ret) {
            System.out.println("r = " + r);
        }
    }

    @Test
    public void parserTest1(){
        ExcelParser ep = new ExcelParser();
        List<Schedule> ret = ep.parse("수1(), 금1~2(N411)");
        for (Schedule r : ret) {
            System.out.println("r = " + r);
        }
    }
}