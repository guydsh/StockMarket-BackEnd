package com.smc.sp.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "tbl_stock_price")
public class StockPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_code", nullable = false)
    private String companyCode;

    @Column(name = "stock_exchange", nullable = false)
    private String stockExchange;

    @Column(name = "current_price", nullable = false)
    private Float currentPrice = 0.0f;

    @Column(name = "stock_timestamp", nullable = false)
    private Date stockTimestamp;

}
