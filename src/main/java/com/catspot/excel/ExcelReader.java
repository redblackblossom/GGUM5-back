package com.catspot.excel;

import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class ExcelReader {
    public void importClassrooms(InputStream inputStream) {
        try {
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            // test
            for (int rowIndex = 2; rowIndex <= 10; rowIndex++) {
                Row row = sheet.getRow(rowIndex);

                // 행이 null일 수 있으므로 체크
                if (row == null) continue;

                // 교과목명
                Cell nameCell = row.getCell(3);

                // 요일및교시(강의실)
                Cell scheduleCell = row.getCell(9);

                // 셀이 null일 경우도 대비
                String schedule = (scheduleCell != null) ? scheduleCell.getStringCellValue() : "";
                String name = (nameCell != null) ? nameCell.getStringCellValue() : "";

                System.out.println(name + " " + schedule);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 엔티티 매핑, 강의실 저장
}
