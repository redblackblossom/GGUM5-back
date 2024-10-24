package com.catspot.excel;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class ExcelParser {

    public List<Schedule> parse(String str) {
        return Arrays.stream(str.split(","))
                .map(String::trim)
                .flatMap(s -> parseSchedule(s).stream())
                .collect(Collectors.toList());
    }

    private List<Schedule> parseSchedule(String s) {
        String day = String.valueOf(s.charAt(0));
        s = s.substring(1);

        String location = extractLocation(s);
        String timeStr = s.substring(0, s.indexOf('('));

        return extractTime(timeStr).stream()
                .map(time -> new Schedule(day, time, location))
                .collect(Collectors.toList());
    }

    private String extractLocation(String str) {
        int st = str.indexOf('(');
        int en = str.indexOf(')');
        return str.substring(st + 1, en);
    }

    private List<String> extractTime(String timeStr) {
        int idx = timeStr.indexOf('~');
        if (idx == -1) {
            return List.of(timeStr);
        }

        int st = Integer.parseInt(timeStr.substring(0, idx));
        int en = Integer.parseInt(timeStr.substring(idx + 1));
        return IntStream.rangeClosed(st, en)
                .mapToObj(String::valueOf)
                .collect(Collectors.toList());
    }
}
