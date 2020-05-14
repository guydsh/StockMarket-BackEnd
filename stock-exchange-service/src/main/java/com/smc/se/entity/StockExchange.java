package com.smc.se.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "tbl_stock_exchange")
public class StockExchange extends Auditable {

    @Column(name = "stock_exchange", nullable = false)
    private String stockExchange;

    @Column(name = "brief", nullable = false)
    private String brief;

    @Column(name = "contact_address", nullable = false)
    private String contactAddress;

    @Column(name = "remarks")
    private String remarks;

}
