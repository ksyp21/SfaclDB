package com.sfacl.magnus.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "newPassword")
public class RequestPasswordChange {

    @Id
    @GeneratedValue()
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;


    @Column(name = "oldPassword")
    private String oldPassword;

    @Column(name = "newPassword")
    private String newPassword;


}


