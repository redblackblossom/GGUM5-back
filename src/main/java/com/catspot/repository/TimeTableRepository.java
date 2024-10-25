package com.catspot.repository;

import com.catspot.entity.TimeTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TimeTableRepository extends JpaRepository<TimeTable, Long> {
    @Query("SELECT t FROM TimeTable t " +
            "JOIN FETCH t.classroom c " +
            "JOIN FETCH t.sessionTime s " +
            "WHERE c.buildingName = :buildingName " +
            "AND c.classroomNumber = :classroomNumber " +
            "AND t.day = :day")
    List<TimeTable> findByClassroomName(@Param("buildingName") String buildingName, @Param("classroomNumber") int classroomNumber, @Param("day") String day);
}
