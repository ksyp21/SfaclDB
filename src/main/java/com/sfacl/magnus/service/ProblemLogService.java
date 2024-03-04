package com.sfacl.magnus.service;

import com.sfacl.magnus.entity.Problem;
import com.sfacl.magnus.entity.ProblemLog;


import java.time.LocalDateTime;
import java.util.List;

public interface ProblemLogService {
    void createProblemLogs(ProblemLog problemLog);
    List<ProblemLog> getAllProblemLogs();

    ProblemLog getProblemLogById(Long id);

    String updateProblemById(Long id, ProblemLog problemLog);


    void createProblemLogForProblemAssignment(
                Problem problem,
                Long newlyAssignedStaffId,
                Long previousAssignedStaffId,
                Long newSolvedById,
                LocalDateTime newAssignedAt,
                LocalDateTime newSolvedAt,
                String newMethodUsed
    );
    void createProblemLogs(List<ProblemLog> problemLogs);




}
