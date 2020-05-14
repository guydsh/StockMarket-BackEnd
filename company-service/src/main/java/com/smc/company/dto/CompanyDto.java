package com.smc.company.dto;

import com.smc.company.entity.StockExchange;
import lombok.Data;

import java.util.List;

@Data
public class CompanyDto {
    private Long id;
    private String companyName;
    private String turnover;
    private String ceo;
    private String directors;
    private Long sectorId;
    private String sectorName;
    private String brief;
    private Boolean active;
}
