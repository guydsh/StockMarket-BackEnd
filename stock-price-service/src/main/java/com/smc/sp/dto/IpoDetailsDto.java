package com.smc.sp.dto;

import lombok.Data;

import java.util.Date;

@Data
public class IpoDetailsDto {

    private Long id;

    private Long companyId;

    private String companyName;

    private String companyCode;

    private Long stockExchangeId;

    private String stockExchangeName;

    private Float pricePerShare = 0.0f;

    private Integer totalShare = 0;

    private Date openDatetime;

    private String remarks;

}
