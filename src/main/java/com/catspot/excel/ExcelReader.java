package com.catspot.excel;

import com.catspot.entity.Classroom;
import com.catspot.entity.SessionTime;
import com.catspot.entity.TimeTable;
import com.catspot.exceptionhandler.CommonErrorCode;
import com.catspot.exceptionhandler.CustomException;
import com.catspot.repository.ClassroomRepository;
import com.catspot.repository.SessionTimeRepository;
import com.catspot.repository.TimeTableRepository;
import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ExcelReader {
    private static final int NAMECELLNUMBER = 3;
    private static final int SCHEDULECELLNUMBER = 9;

    private final ExcelParser excelParser;
    private final ClassroomRepository classroomRepository;
    private final TimeTableRepository timeTableRepository;
    private final SessionTimeRepository sessionTimeRepository;

    public void importClassrooms(Workbook workbook) {

        Sheet sheet = workbook.getSheetAt(0);
        for (int rowIndex = 2; rowIndex < sheet.getLastRowNum(); rowIndex++) {
            Row row = sheet.getRow(rowIndex);
            if (row == null) continue;
            // 교과목명
            String name = row.getCell(NAMECELLNUMBER).getStringCellValue().trim();

            // 요일및교시(강의실)
            String schedule = row.getCell(SCHEDULECELLNUMBER).getStringCellValue().trim();

            if (name.isEmpty() || schedule.isEmpty()) continue;

            saveColumnData(excelParser.parse(schedule), name);
        }

    }

    private void saveColumnData(List<Schedule> scheduleList, String name){
        for (Schedule s : scheduleList) {
            Optional<Classroom> existingClassroom = getClassroom(s);

            Classroom classroom = existingClassroom.orElseGet(() -> classroomRepository.save(s.getLocation()));;

            SessionTime sessionTime = sessionTimeBuilder(s);
            sessionTimeRepository.save(sessionTime);

            TimeTable timeTable = timeTableBuilder(s, name ,classroom, sessionTime);
            timeTableRepository.save(timeTable);
        }
    }

    private Optional<Classroom> getClassroom(Schedule s) {
        Optional<Classroom> existingClassroom = classroomRepository.findByBuildingNameAndClassroomNumber(s.getLocation().getBuildingName(), s.getLocation().getClassroomNumber());
        return existingClassroom;
    }

    private SessionTime sessionTimeBuilder(Schedule s) {
        SessionTime sessionTime = SessionTime.builder()
                .time(s.getTime())
                .build();
        return sessionTime;
    }

    private TimeTable timeTableBuilder(Schedule s, String name ,Classroom classroom, SessionTime sessionTime) {
        TimeTable timeTable = TimeTable.builder()
                .day(s.getDay())
                .courseName(name)
                .classroom(classroom)
                .sessionTime(sessionTime)
                .build();
        return timeTable;
    }

}
