package com.sfacl.magnus.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Branch")
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "branch_id")
    private Long id;

    @Column(name = "branch_address1")
    private String address1;
    @Column(name = "branch_address2")
    private String address2;

    @ManyToOne
    @JoinColumn(name = "sfacl_id")
    private Sfacl sfacl;


}
