package com.sfacl.magnus.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "user_record")
public class UserRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "login_time")
    private LocalDateTime loginTime;

    @Column(name = "logout_time")
    private LocalDateTime logoutTime;

    @Column(name = "activity_type")
    @Enumerated
    private ActivityType activityType;

    @Column(name = "logout_type")
    @Enumerated
    private LogoutType logoutType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;




}
