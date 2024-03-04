package com.sfacl.magnus.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "Problem")
public class Problem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "problem_id")
    private Long id;


    @Column(name = "p_status")
    @Enumerated(EnumType.ORDINAL)
    private ProblemStatus status;

    @Column(name = "p_description")
    private String problemDescription;

    @Column(name = "s_description")
    private String solutionDesctiption;

    @ManyToOne
    private Staff staff;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "pr_fk",referencedColumnName = "problem_id")
    private List<ProblemLog> problemLogs;




}
