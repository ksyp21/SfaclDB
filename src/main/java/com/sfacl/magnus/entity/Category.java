package com.sfacl.magnus.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "Category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "category_id")
    private int id;

    @Column(name = "categroy_name")
    private String categoryName;


    @Column(name = "category_description")
    private String categoryDescription;



}
