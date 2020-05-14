package com.smc.company.vo;

import com.smc.company.entity.StockExchange;
import lombok.Data;

import java.util.List;

@Data
public class CompanyVo {
    private String companyName;
    private String turnover;
    private String ceo;
    private String directors;
    private Long sectorId;
    private String sectorName;
    private String sector;
    private String brief;
}
