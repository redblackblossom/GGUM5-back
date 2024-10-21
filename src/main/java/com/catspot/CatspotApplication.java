package com.catspot;

import com.catspot.excel.ExcelReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

@SpringBootApplication
public class CatspotApplication implements CommandLineRunner {
	@Autowired
	private ExcelReader excelReader;

	public static void main(String[] args) {
		SpringApplication.run(CatspotApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ClassPathResource resource = new ClassPathResource("/course/개설과목리스트_20241019175059.xlsx");
		excelReader.importClassrooms(resource.getInputStream());
	}

}
