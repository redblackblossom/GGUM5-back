package com.catspot.excel;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ExcelParserTest {

    @Test
    public void 포맷이_두개지요(){
        ExcelParser ep = new ExcelParser();
        Schedule[] ret = ep.parse("수1(N411), 금1~2(N311)").toArray(new Schedule[0]);

        assertEquals(ret.length, 3);
        check(ret[0], "수", 1, "N", "411");
        check(ret[1], "금", 1, "N", "311");
        check(ret[2], "금", 2, "N", "311");

    }

    @Test
    public void 단일_포맷_일반(){
        ExcelParser ep = new ExcelParser();
        Schedule[] ret = ep.parse("수1(N123)").toArray(new Schedule[0]);
        assertEquals(ret.length, 1);
        check(ret[0], "수", 1, "N", "123");
    }
    
    @Test
    public void 단일_포맷_강의실없음(){
        ExcelParser ep = new ExcelParser();
        Schedule[] ret = ep.parse("수1()").toArray(new Schedule[0]);
        assertEquals(ret.length, 1);
        check(ret[0], "수", 1, null, null);
    }

    @Test
    public void 테니스장은_몰랐지(){
        //테니스장, 헬스장, 체육관
        ExcelParser ep = new ExcelParser();
        Schedule[] ret = ep.parse("월3(테니스장)").toArray(new Schedule[0]);
        check(ret[0], "월", 3, "테니스장",null);
    }


    public void check(Schedule s, String day, Integer time, String buildingName , String classroomNumber){
        assertEquals(Integer.parseInt(s.getTime()),time);
        assertEquals(s.getDay(),day);

        String bname = s.getLocation().getBuildingName();
        assertEquals(bname,buildingName);

        Integer cnum = s.getLocation().getClassroomNumber();
        if(cnum==null){
            assertEquals(cnum, classroomNumber);
        }
        else{
            assertEquals(Integer.toString(cnum),classroomNumber);
        }

    }
}