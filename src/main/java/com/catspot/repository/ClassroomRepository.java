package com.catspot.repository;

import com.catspot.entity.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
    Optional<Classroom> findByBuildingNameAndClassroomNumber(String buildingName, Integer classroomNumber);

    @Query("select distinct c from Classroom c " +
            "join fetch c.timeTables t " +
            "where c.buildingName = :buildingName " +
            "and cast(c.classroomNumber as string) like concat(:floor, '%') " +
            "and t.day = :day " +
            "and t.sessionTime.time = :time")
    List<Classroom> findByBuildingNameAndFloorAndDayAndTime(String buildingName, int floor, String day, int time);
}
