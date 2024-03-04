package com.sfacl.magnus.entity;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "Address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "address_id")
    private Long addressId;

    @Column(name = "district_name")
    private String districtName;

    @Column(name = "address1")
    private String address1;

    @Column(name = "address2")
    private String address2;

    @Column(name = "postal_code")
    private String postalCode;

}