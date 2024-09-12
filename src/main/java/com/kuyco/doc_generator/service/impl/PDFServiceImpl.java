package com.kuyco.doc_generator.service.impl;

import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.kuyco.doc_generator.dto.TransactionReportDto;
import com.kuyco.doc_generator.service.PDFService;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.time.Instant;

@Service
public class PDFServiceImpl implements PDFService {
    @Override
    public void generate(TransactionReportDto transactionReportDto) {
        try {
            String outputPath = "pdf_" + Instant.now().toEpochMilli() + "_tx_" + transactionReportDto.getId() + ".pdf";

            PdfWriter writer = new PdfWriter(new FileOutputStream(outputPath));
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            document.add(new Paragraph("Transaction Report").setBold().setFontSize(14));

            float[] columnWidths = {50f, 50f, 100f, 150f, 60f, 60f, 60f, 60f};
            Table table = new Table(columnWidths);
            table.setAutoLayout();

            addTableHeader(table);

            table.addCell(new Cell().add(new Paragraph(String.valueOf(transactionReportDto.getId()))));
            table.addCell(new Cell().add(new Paragraph(String.valueOf(transactionReportDto.getCustomerId()))));
            table.addCell(new Cell().add(new Paragraph(transactionReportDto.getCustomerName())));
            table.addCell(new Cell().add(new Paragraph(String.join(", ", transactionReportDto.getItemNames()))));
            table.addCell(new Cell().add(new Paragraph(String.valueOf(transactionReportDto.getCustomerChange()))));
            table.addCell(new Cell().add(new Paragraph(String.valueOf(transactionReportDto.getAmount()))));
            table.addCell(new Cell().add(new Paragraph(String.valueOf(transactionReportDto.getCustomerOldBalance()))));
            table.addCell(new Cell().add(new Paragraph(String.valueOf(transactionReportDto.getCustomerNewBalance()))));

            document.add(table);

            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addTableHeader(Table table) {
        Color headerColor = new DeviceRgb(140, 221, 8);
        table.addHeaderCell(new Cell().add(new Paragraph("Transaction ID")).setBackgroundColor(headerColor));
        table.addHeaderCell(new Cell().add(new Paragraph("Customer ID")).setBackgroundColor(headerColor));
        table.addHeaderCell(new Cell().add(new Paragraph("Customer Name")).setBackgroundColor(headerColor));
        table.addHeaderCell(new Cell().add(new Paragraph("List Item Name")).setBackgroundColor(headerColor));
        table.addHeaderCell(new Cell().add(new Paragraph("Customer Change")).setBackgroundColor(headerColor));
        table.addHeaderCell(new Cell().add(new Paragraph("Total Cost")).setBackgroundColor(headerColor));
        table.addHeaderCell(new Cell().add(new Paragraph("Customer Old Balance")).setBackgroundColor(headerColor));
        table.addHeaderCell(new Cell().add(new Paragraph("Customer New Balance")).setBackgroundColor(headerColor));
    }
}
