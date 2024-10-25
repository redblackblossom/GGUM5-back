package com.catspot.studyplace;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyPlaceRepository extends JpaRepository<StudyPlace, Long> {
}
