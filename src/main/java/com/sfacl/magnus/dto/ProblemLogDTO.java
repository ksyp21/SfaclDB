package com.sfacl.magnus.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProblemLogDTO {
    private Long assignedTo;
    private Long assignedBy;
    private Long solvedBy;
    private String methodUsed;
    private LocalDateTime assignedAt;
    private LocalDateTime resolvedAt;

    // Getters and setters
}
