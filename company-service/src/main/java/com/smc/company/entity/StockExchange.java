package com.smc.company.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tbl_stock_exchange")
public class StockExchange extends Auditable {

    @Column(name = "stock_exchange_id", nullable = false)
    private Long stockExchangeId;

    @Column(name = "stock_exchange", nullable = false)
    private String stockExchange;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

}
