package com.catspot.excel;

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
    public void importClassrooms(InputStream inputStream) {
        try {
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            for (int rowIndex = 2; rowIndex < sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                if (row == null) continue;
                // 교과목명
                Cell nameCell = row.getCell(NAMECELLNUMBER);

                // 요일및교시(강의실)
                Cell scheduleCell = row.getCell(SCHEDULECELLNUMBER);

                if (scheduleCell == null || scheduleCell.getStringCellValue().trim().isEmpty() ||
                        nameCell == null || nameCell.getStringCellValue().trim().isEmpty()) {
                    continue;
                }
                String schedule = scheduleCell.getStringCellValue();
                String name = nameCell.getStringCellValue();

                List<Schedule> scheduleList = excelParser.parse(schedule);

                for (Schedule s : scheduleList) {
                    Optional<Classroom> existingClassroom = getClassroom(s);

                    Classroom classroom;
                    if (existingClassroom.isPresent()) {
                        classroom = existingClassroom.get();
                    } else {
                        classroom = classroomRepository.save(s.getLocation());
                    }

                    SessionTime sessionTime = sessionTimeBuilder(s);
                    sessionTimeRepository.save(sessionTime);

                    TimeTable timeTable = timeTableBuilder(s, name ,classroom, sessionTime);
                    timeTableRepository.save(timeTable);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Optional<Classroom> getClassroom(Schedule s) {
        Optional<Classroom> existingClassroom = classroomRepository.findByClassroomName(s.getLocation().getClassroomName());
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
