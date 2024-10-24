package com.catspot.excel;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer classroomNumber = null;
    private String buildingName = null;

    public Classroom(String str) {
        if(str.isEmpty()) return;

        if(!Character.isDigit(str.charAt(str.length() - 1))){
            this.buildingName = str;
            return;
        }

        this.classroomNumber = Integer.parseInt(str.substring(str.length()-3));

        this.buildingName = str.substring(0,str.length()-3);
    }

    @OneToMany(mappedBy = "classroom")
    private List<TimeTable> timeTables = new ArrayList<>();

}
