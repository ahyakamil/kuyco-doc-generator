package com.kuyco.doc_generator;

import com.kuyco.doc_generator.dto.TransactionReportDto;
import com.kuyco.doc_generator.service.ExcelService;
import com.kuyco.doc_generator.service.PDFService;
import com.kuyco.doc_generator.service.impl.ExcelServiceImpl;
import com.kuyco.doc_generator.service.impl.PDFServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class DocGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(DocGeneratorApplication.class, args);
	}

}
