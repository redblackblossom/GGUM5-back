package com.catspot.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimeCalculatorTest {

    @Test
    void calculatePeriodTest() {
        // 정상 범위 테스트 (9 ~ 22시)
        assertEquals(1, TimeCalculator.getCurrentTime(9));   // 9시 -> 1교시
        assertEquals(7, TimeCalculator.getCurrentTime(15));  // 15시 -> 7교시
        assertEquals(14, TimeCalculator.getCurrentTime(22)); // 22시 -> 14교시

        // 예외 범위 테스트 (8시, 23시 등)
        assertEquals(-1, TimeCalculator.getCurrentTime(8));  // 9시 미만
        assertEquals(-1, TimeCalculator.getCurrentTime(23)); // 22시 초과
    }
}