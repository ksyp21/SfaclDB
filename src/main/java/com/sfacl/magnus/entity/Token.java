package com.sfacl.magnus.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "token")
@Data
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "token")
    private String token;

    @Column(name = "is_logged_out")
    private boolean loggedOut;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
