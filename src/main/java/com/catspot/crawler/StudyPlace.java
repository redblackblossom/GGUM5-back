package com.catspot.crawler;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class StudyPlace {
    @Id
    private Long placeIdx;
    private String placeName;
    private String url;
    private Integer allSeats;
    private Integer useSeats;
    private Integer restSeats;

    @Override
    public String toString() {
        return "placeIdx=" + placeIdx + ", placeName=" + placeName + ", url=" + url +
                ", allSeats=" + allSeats + ", useSeats=" + useSeats + ", restSeats=" + restSeats;
    }
}
