package com.kuyco.doc_generator.service;

import com.kuyco.doc_generator.dto.TransactionReportDto;

public interface PDFService {
    void generate(TransactionReportDto transactionReportDto);
}
