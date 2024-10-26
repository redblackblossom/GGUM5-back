package com.catspot.excel;

import com.catspot.exceptionhandler.CommonErrorCode;
import com.catspot.exceptionhandler.CustomException;
import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
@AllArgsConstructor
public class ExcelDataInitializer implements CommandLineRunner {
    private final ExcelReader excelReader;

    @Override
    public void run(String... args) throws CustomException {
        ClassPathResource resource = new ClassPathResource("/course/개설과목리스트_20241019175059.xlsx");

        try{
            InputStream inputStream = resource.getInputStream();
            Workbook workbook = WorkbookFactory.create(inputStream);
            excelReader.importClassrooms(workbook);
        } catch (Exception e){
            throw new CustomException(CommonErrorCode.INTERNAL_SERVER_ERROR);
        }

    }
}
