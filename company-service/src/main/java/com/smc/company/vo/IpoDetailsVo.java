package com.smc.company.vo;

import com.smc.company.entity.Auditable;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Data
public class IpoDetailsVo {

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
