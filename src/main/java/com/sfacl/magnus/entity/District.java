package com.sfacl.magnus.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "District")
public class District {
    @Id
    @Column(name = "district_id")
    private Long districtId;

    @Column(name = "district_name")
    private String districtName;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;

}
