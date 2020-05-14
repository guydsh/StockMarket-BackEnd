package com.smc.sp.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class StockPriceChartVo {
    private List<CompanyCodeStockExchangeName> list;
    private Date dateFrom;
    private Date dateTo;
}
