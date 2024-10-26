package com.catspot.excel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.*;

import com.catspot.exceptionhandler.CommonErrorCode;
import com.catspot.exceptionhandler.CustomException;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;



import java.io.InputStream;

@SpringBootTest
class ExcelReaderTest {
    @Autowired
    private ExcelReader excelReader;

    @Test
    void importClassroomsTest() throws Exception {
        ClassPathResource resource = new ClassPathResource("/course/개설과목리스트_20241019175059.xlsx");
        InputStream inputStream = resource.getInputStream();
        Workbook workbook = WorkbookFactory.create(inputStream);
        excelReader.importClassrooms(workbook);
    }




}
