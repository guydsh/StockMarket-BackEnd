package com.smc.sp.dto;

import lombok.Data;

import java.util.Date;

@Data
public class StockPriceDto {

    private String companyCode;

    private String stockExchange;

    private Float currentPrice = 0.0f;

    private Date stockTimestamp;

}
