package com.catspot.util;

public class TimeCalculator {
    private static final int START_HOUR = 9;

    public static int getCurrentTime(int hour) {
        return calculatePeriod(hour);
    }

    private static int calculatePeriod(int hour) {
        if (hour < START_HOUR || hour >= START_HOUR + 14) {
            return -1;
        }
        return hour - START_HOUR + 1;
    }
}
