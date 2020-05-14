package com.smc.company.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "tbl_stock_price")
public class StockPrice extends Auditable {

    @Column(name = "company_id", nullable = false)
    private Company company;

//    @Column(name = "")

}
