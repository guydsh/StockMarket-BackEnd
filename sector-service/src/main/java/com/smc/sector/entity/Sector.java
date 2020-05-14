package com.smc.sector.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "tbl_sector")
public class Sector extends Auditable {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "brief", nullable = false)
    private String brief;

}
