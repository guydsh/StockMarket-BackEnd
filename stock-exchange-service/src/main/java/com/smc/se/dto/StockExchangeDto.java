package com.smc.se.dto;

import lombok.Data;

@Data
public class StockExchangeDto {

    private Long id;

    private String stockExchange;

    private String brief;

    private String contactAddress;

    private String remarks;
}
