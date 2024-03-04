package com.sfacl.magnus.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "ProblemLog")
@Data
public class ProblemLog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "LogId")
    private Long id;


    @ManyToOne
    @JoinColumn(name = "assigned_to_staff_id")
    private Staff assignedTo;


    @ManyToOne
    @JoinColumn(name = "assigned_by_staf_id")
    private Staff assignedBy;

    @ManyToOne
    @JoinColumn(name = "solved_by_id")
    private Staff solvedBy;


    @Column(name = "methods_Used")
    private String methodUsed;

    @Column(name = "assigned_at")
    private LocalDateTime assignedAt;

    @Column(name = "active_days")
    private Integer activeDays;


    @ManyToOne
    @JoinColumn(name = "problem_id")
    private Problem problem;

    @Column(name = "solved_at")
    private LocalDateTime resolvedAt;
    @PrePersist
    @PreUpdate
    private void calculateActiveDays() {
        if (assignedAt != null && resolvedAt != null) {
            activeDays = (int) ChronoUnit.DAYS.between(assignedAt.toLocalDate(), resolvedAt.toLocalDate());
        }
    }


    }
