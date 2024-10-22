package com.catspot.excel;

import org.junit.jupiter.api.Test;
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

        excelReader.importClassrooms(inputStream);
    }

}
