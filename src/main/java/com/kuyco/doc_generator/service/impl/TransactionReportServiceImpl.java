package com.kuyco.doc_generator.service.impl;

import com.kuyco.doc_generator.dto.TransactionReportDto;
import com.kuyco.doc_generator.service.ExcelService;
import com.kuyco.doc_generator.service.PDFService;
import com.kuyco.doc_generator.service.TransactionReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class TransactionReportServiceImpl implements TransactionReportService {
    @Autowired
    private ExcelService excelService;

    @Autowired
    private PDFService pdfService;

    @KafkaListener(topics = "${topic.transaction.report}", groupId = "${consumer.group.transaction.report}")
    public void generateTransactionReport(TransactionReportDto transactionReportDto) {
        excelService.generate(transactionReportDto);
        pdfService.generate(transactionReportDto);
    }
}
