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
    private Integer classroomNumber;
    private String classroomName;
    private String buildingName;

    public Classroom(String str) {
        this.classroomName = str;

        this.classroomNumber = Integer.parseInt(str.substring(str.length()-3));

        this.buildingName = str.substring(0,str.length()-3);
    }

    @OneToMany(mappedBy = "classroom")
    private List<TimeTable> timeTables = new ArrayList<>();

}
