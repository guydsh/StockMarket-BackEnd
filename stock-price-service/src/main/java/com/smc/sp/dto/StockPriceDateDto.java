package com.smc.sp.dto;

import lombok.Data;

import java.util.Date;

@Data
public class StockPriceDateDto {

    private Float currentPrice = 0.0f;

    private Date stockTimestamp;

}
