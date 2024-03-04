package com.sfacl.magnus.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Region")
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "region_id")
    private Long id;

    @Column(name = "region_name")
    private String regionName;
    @Column(name = "province_name")
    private String provinceName;



}
