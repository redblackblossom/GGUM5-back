package com.catspot.excel;

import lombok.Data;

@Data
public class Classroom {
    private String classroom_name;
    private Integer classroom_number;
    private String building_name;

    public Classroom(String str) {
        this.classroom_name = str;

        this.classroom_number = Integer.parseInt(str.substring(str.length()-3));

        this.building_name = str.substring(0,str.length()-3);
    }

}
