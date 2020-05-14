package com.smc.se.vo;

import lombok.Data;

import javax.persistence.Column;

@Data
public class StockExchangeVo {

    private String stockExchange;

    private String brief;

    private String contactAddress;

    private String remarks;

}
