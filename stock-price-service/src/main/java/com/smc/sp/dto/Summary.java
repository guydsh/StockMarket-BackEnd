package com.smc.sp.dto;

import lombok.Data;

import java.util.Date;

@Data
public class Summary {

    private String companyName;

    private String companyCode;

    private String stockExchange;

    private Integer totalImported;

    private Date dateFrom;

    private Date dateTo;

}
