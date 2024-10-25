package com.catspot.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.Locale;

public class TimeCalculator {
    private static String testDay = null;
    private static Integer testTime = null;
    private static final int START_HOUR = 9;

    public static int getCurrentTime() {
        LocalTime now = LocalTime.now();
        int hour = now.getHour();

        return (testTime != null) ? testTime : calculatePeriod(hour);
    }

    public static String getCurrentDay(){
        DayOfWeek dayOfWeek = LocalDate.now().getDayOfWeek();
        String day = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.KOREAN);
        return (testDay != null) ? testDay: day;
    }

    public static void resetTestConditions() {
        testDay = null;
        testTime = null;
    }

    public static void setTestConditions(String day, Integer time) {
        testDay = day;
        testTime = time;
    }

    private static int calculatePeriod(int hour) {
        if (hour < START_HOUR || hour >= START_HOUR + 14) {
            return -1;
        }
        return hour - START_HOUR + 1;
    }
}
