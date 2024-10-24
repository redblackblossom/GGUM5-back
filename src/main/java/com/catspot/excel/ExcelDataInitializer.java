package com.catspot.excel;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
@AllArgsConstructor
public class ExcelDataInitializer implements CommandLineRunner {
    private final ExcelReader excelReader;

    @Override
    public void run(String... args) throws Exception {
        ClassPathResource resource = new ClassPathResource("/course/개설과목리스트_20241019175059.xlsx");
        InputStream inputStream = resource.getInputStream();

        excelReader.importClassrooms(inputStream);
    }
}
