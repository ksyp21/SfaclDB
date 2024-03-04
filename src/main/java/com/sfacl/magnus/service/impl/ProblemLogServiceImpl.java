package com.sfacl.magnus.service.impl;

import com.sfacl.magnus.entity.Problem;
import com.sfacl.magnus.entity.ProblemLog;
import com.sfacl.magnus.entity.Staff;
import com.sfacl.magnus.repository.ProblemLogRepository;
import com.sfacl.magnus.repository.StaffRepository;
import com.sfacl.magnus.service.ProblemLogService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProblemLogServiceImpl implements ProblemLogService {



    private ProblemLogRepository problemLogRepository;


    private StaffRepository staffRepository;

    public ProblemLogServiceImpl(ProblemLogRepository problemLogRepository, StaffRepository staffRepository) {
        this.problemLogRepository = problemLogRepository;
        this.staffRepository = staffRepository;
    }

    @Override
    public void createProblemLogs(ProblemLog problemLog) {
        problemLogRepository.save(problemLog);
    }

    @Override
    public List<ProblemLog> getAllProblemLogs() {
        return problemLogRepository.findAll();
    }

    @Override
    public ProblemLog getProblemLogById(Long id) {
        Optional<ProblemLog> optionalProblemLog = problemLogRepository.findById(id);
        return optionalProblemLog.orElse(null);
    }

    @Override
    public String updateProblemById(Long id, ProblemLog problemLog) {
        Optional<ProblemLog> optionalProblemLog = problemLogRepository.findById(id);
        if(optionalProblemLog.isPresent()){
            ProblemLog existingProblemLog= optionalProblemLog.get();
            existingProblemLog = optionalProblemLog.get();
            existingProblemLog.setAssignedAt(problemLog.getAssignedAt());
            existingProblemLog.setAssignedBy(problemLog.getAssignedBy());
            existingProblemLog.setAssignedTo(problemLog.getAssignedTo());
            existingProblemLog.setMethodUsed(problemLog.getMethodUsed());
            existingProblemLog.setResolvedAt(problemLog.getResolvedAt());
            existingProblemLog.setSolvedBy(existingProblemLog.getSolvedBy());
            problemLogRepository.save(problemLog);

        }
        return null;
    }

    @Override
    public void createProblemLogForProblemAssignment(
            Problem problem,
            Long newlyAssignedStaffId,
            Long previousAssignedStaffId,
            Long newSolvedById,
            LocalDateTime newAssignedAt,
            LocalDateTime newSolvedAt,
            String newMethodUsed) {

        // Fetch Staff objects from the database using the provided IDs
        Staff newlyAssignedStaff = staffRepository.findById(newlyAssignedStaffId).orElse(null);
        Staff previousAssignedStaff = staffRepository.findById(previousAssignedStaffId).orElse(null);
        Staff newSolvedBy = staffRepository.findById(newSolvedById).orElse(null);

        if (newlyAssignedStaff == null || previousAssignedStaff == null || newSolvedBy == null) {
            // Handle the case when any of the Staff objects are not found
            // You can log an error or throw an exception as appropriate
            return;
        }

        ProblemLog problemLog = new ProblemLog();
        problemLog.setProblem(problem);
        problemLog.setAssignedTo(newlyAssignedStaff); // Set the Staff object instead of ID
        problemLog.setAssignedBy(previousAssignedStaff); // Set the Staff object instead of ID
        problemLog.setSolvedBy(newSolvedBy); // Set the Staff object instead of ID
        problemLog.setAssignedAt(newAssignedAt);
        problemLog.setResolvedAt(newSolvedAt);
        problemLog.setMethodUsed(newMethodUsed);

        problemLogRepository.save(problemLog);
    }
    @Override
    public void createProblemLogs(List<ProblemLog> problemLogs) {
        problemLogRepository.saveAll(problemLogs);
    }







}
