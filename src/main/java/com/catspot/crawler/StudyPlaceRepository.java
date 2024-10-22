package com.catspot.crawler;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyPlaceRepository extends JpaRepository<StudyPlace, Long> {
}
