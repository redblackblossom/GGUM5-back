package com.catspot.excel;

import com.catspot.entity.Classroom;
import lombok.Data;

@Data
public class Schedule {
    private String day;
    private String time;
    private Classroom location;

    public Schedule(String day, String time, String location) {
        this.day = day;
        this.time = time;
        this.location = new Classroom(location);
    }
}
