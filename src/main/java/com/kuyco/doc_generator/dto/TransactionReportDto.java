package com.kuyco.doc_generator.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TransactionReportDto {
    private Long id;
    private Long customerId;
    private String customerName;
    private Double customerChange;
    private Double amount;
    private Double customerOldBalance;
    private Double customerNewBalance;
    private List<String> itemNames;
}
