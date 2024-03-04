package com.sfacl.magnus.controller;

import com.sfacl.magnus.dto.ProblemDTO;
import com.sfacl.magnus.dto.ProblemLogDTO;
import com.sfacl.magnus.entity.Problem;
import com.sfacl.magnus.entity.ProblemLog;
import com.sfacl.magnus.entity.Staff;
import com.sfacl.magnus.service.ProblemLogService;
import com.sfacl.magnus.service.ProblemService;
import com.sfacl.magnus.service.StaffService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProblemLogController {


    private ProblemLogService problemLogService;


    private ProblemService problemService;


    private StaffService staffService;

    public ProblemLogController(ProblemLogService problemLogService, ProblemService problemService, StaffService staffService) {
        this.problemLogService = problemLogService;
        this.problemService = problemService;
        this.staffService = staffService;
    }

    @PostMapping("/problemLogs")
    public ResponseEntity<String> createProblemLog(@RequestBody ProblemLog problemLog){
        problemLogService.createProblemLogs(problemLog);
        return ResponseEntity.ok("Created");
    }

    @GetMapping("/problemLogs")
    public ResponseEntity<List<ProblemLog>> findallproblemlogs(){
        List<ProblemLog> problemLogs = problemLogService.getAllProblemLogs();
        return new ResponseEntity<>(problemLogs, HttpStatus.OK);
    }
    @PostMapping("/problems/{problemId}/logs")
    public ResponseEntity<String> createProblemLogForProblemAssignment(
            @PathVariable Long problemId,
            @RequestBody ProblemDTO problemDTO) {

        Problem problem = problemService.getProblemById(problemId);

        if (problem != null) {
            List<ProblemLogDTO> problemLogDTOs = problemDTO.getProblemLogs();
            List<ProblemLog> problemLogs = new ArrayList<>();

            for (ProblemLogDTO logDTO : problemLogDTOs) {
                ProblemLog problemLog = new ProblemLog();
                problemLog.setProblem(problem);

                // Fetch the staff objects from the service layer
                Optional<Staff> assignedToStaff = staffService.getStaffById(logDTO.getAssignedTo());
                Optional<Staff> assignedByStaff = staffService.getStaffById(logDTO.getAssignedBy());
                Optional<Staff> solvedByStaff = staffService.getStaffById(logDTO.getSolvedBy());

                // Check if the staff objects are present in the Optional
                if (assignedToStaff.isPresent() && assignedByStaff.isPresent() && solvedByStaff.isPresent()) {
                    problemLog.setAssignedTo(assignedToStaff.get());
                    problemLog.setAssignedBy(assignedByStaff.get());
                    problemLog.setSolvedBy(solvedByStaff.get());
                } else {
                    // Handle the case where the staff objects are not found
                    return new ResponseEntity<>("Staff not found", HttpStatus.NOT_FOUND);
                }

                problemLog.setMethodUsed(logDTO.getMethodUsed());
                problemLog.setAssignedAt(logDTO.getAssignedAt());
                problemLog.setResolvedAt(logDTO.getResolvedAt());
                problemLogs.add(problemLog);
            }

            problemLogService.createProblemLogs(problemLogs);
            return new ResponseEntity<>("Problem logs created for problem assignment", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Problem not found", HttpStatus.NOT_FOUND);
        }
    }

}
