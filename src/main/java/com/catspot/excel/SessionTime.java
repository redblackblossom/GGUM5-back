package com.catspot.excel;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class SessionTime {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String time;

    @OneToMany(mappedBy = "sessionTime")
    private List<TimeTable> timeTables = new ArrayList<>();

    @Builder
    public SessionTime(String time) {
        this.time = time;
    }

}
