package com.smc.company.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "tbl_ipo_details")
public class IpoDetails extends Auditable {

    @Column(name = "company_id", nullable = false)
    private Long companyId;

    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Column(name = "company_code", nullable = false)
    private String companyCode;

    @Column(name = "stock_exchange_id", nullable = false)
    private Long stockExchangeId;

    @Column(name = "stock_exchange_name", nullable = false)
    private String stockExchangeName;

    @Column(name = "price_per_share", nullable = false)
    private Float pricePerShare = 0.0f;

    @Column(name = "total_share", nullable = false)
    private Integer totalShare = 0;

    @Column(name = "open_datetime", nullable = false)
    private Date openDatetime;

    @Column(name = "remarks")
    private String remarks;

}
