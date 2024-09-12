package com.kuyco.doc_generator.service;

import com.kuyco.doc_generator.dto.TransactionReportDto;

public interface TransactionReportService {
    void generateTransactionReport(TransactionReportDto transactionReportDto);
}
