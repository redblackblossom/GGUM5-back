package com.catspot.studyplace;

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
}
