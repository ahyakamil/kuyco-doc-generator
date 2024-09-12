package com.kuyco.doc_generator.service.impl;

import com.kuyco.doc_generator.dto.TransactionReportDto;
import com.kuyco.doc_generator.service.ExcelService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;

@Service
public class ExcelServiceImpl implements ExcelService {
    private static final String TEMPLATE_PATH = "src/main/resources/transaction_template.xlsx";

    @Override
    public void generate(TransactionReportDto transactionReportDto) {
        try (FileInputStream fis = new FileInputStream(TEMPLATE_PATH);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            int rowNum = 1;

            Row row = sheet.getRow(rowNum);
            if (row == null) {
                row = sheet.createRow(rowNum);
            }

            fillCell(row, 0, String.valueOf(transactionReportDto.getId()));
            fillCell(row, 1, String.valueOf(transactionReportDto.getCustomerId()));
            fillCell(row, 2, transactionReportDto.getCustomerName());
            fillCell(row, 3, String.join(", ", transactionReportDto.getItemNames()));
            fillCell(row, 4, transactionReportDto.getCustomerChange());
            fillCell(row, 5, transactionReportDto.getAmount());
            fillCell(row, 6, transactionReportDto.getCustomerOldBalance());
            fillCell(row, 7, transactionReportDto.getCustomerNewBalance());

            String outputPath = "excel_" + Instant.now().toEpochMilli() + "_tx_" + transactionReportDto.getId() + ".xlsx";
            try (FileOutputStream fos = new FileOutputStream(outputPath)) {
                workbook.write(fos);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void fillCell(Row row, int cellNum, Object value) {
        Cell cell = row.getCell(cellNum);
        if (cell == null) {
            cell = row.createCell(cellNum);
        }

        CellStyle style = cell.getCellStyle();

        if (value instanceof String) {
            cell.setCellValue((String) value);
        } else if (value instanceof Double) {
            cell.setCellValue((Double) value);
        } else if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        }

        cell.setCellStyle(style);
    }
}
