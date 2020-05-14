package com.smc.sp.dto;

import com.smc.sp.entity.StockPrice;
import lombok.Data;

import java.util.List;

@Data
public class StockPriceChartDto {
    private String companyName;
    private String companyCode;
    private String stockExchange;
    private List<StockPriceDateDto> list;
}
