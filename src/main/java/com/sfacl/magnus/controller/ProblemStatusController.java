package com.sfacl.magnus.controller;

import com.sfacl.magnus.entity.Problem;
import com.sfacl.magnus.service.impl.ProblemStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProblemStatusController {

    private ProblemStatus problemStatus;

    public ProblemStatusController(ProblemStatus problemStatus) {
        this.problemStatus = problemStatus;
    }

    @GetMapping("/solved-problems")
    public ResponseEntity<List<Problem>> getSolvedProblems() {
        List<Problem> solvedProblems = problemStatus.getSolvedProblems();
        return ResponseEntity.ok(solvedProblems);
    }

    @GetMapping("/unsolved-problems")
    public ResponseEntity<List<Problem>> getUnsolvedProblems(){
        List<Problem> unsolvedProvlems = problemStatus.getUnsolvedSolvedProblems();
        return ResponseEntity.ok(unsolvedProvlems);
    }


}
