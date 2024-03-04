package com.sfacl.magnus.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProblemDTO {
    private String status;
    private String problemDescription;
    private List<ProblemLogDTO> problemLogs;

    // Getters and setters
}



