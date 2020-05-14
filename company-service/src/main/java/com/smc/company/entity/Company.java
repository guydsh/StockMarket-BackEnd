package com.smc.company.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "tbl_company")
public class Company extends Auditable {

    @Column(name = "company_name", nullable = false, unique = true)
    private String companyName;

    @Column(name = "turnover", nullable = false)
    private String turnover;

    @Column(name = "ceo", nullable = false)
    private String ceo;

    @Column(name = "directors")
    private String directors;

    @Column(name = "sector_id")
    private Long sectorId;

    @Column(name = "sector_name")
    private String sectorName;

    @Column(name = "brief")
    private String brief;

    @Column(name = "active")
    private Boolean active = true;

}
