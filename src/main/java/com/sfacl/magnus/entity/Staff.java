package com.sfacl.magnus.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "Staff")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "staff_id")
    private Long id;
    @Column(name = "staff_firstname")
    private String firstname;
    @Column(name = "staff_middlename")
    private String middlename;
    @Column(name = "staff_lastname")
    private String lastname;
    @Column(name = "staff_phonenumber")
    private String phonenumber;


    @OneToMany(targetEntity = Problem.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "staff_id",referencedColumnName = "staff_id")
    private List<Problem> problem;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "st_fk",referencedColumnName = "staff_id")
    private List<Address> address;


    // Many-to-one relationship with Category
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


}
