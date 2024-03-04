package com.sfacl.magnus.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name="Sfacl")
public class Sfacl {
    @Id
    @Column(name = "sfacl_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "sfacl_name")
    private String name;

    @OneToMany(targetEntity = Problem.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "sfacl_id",referencedColumnName = "sfacl_id")
    private List<Problem> problems;

}
