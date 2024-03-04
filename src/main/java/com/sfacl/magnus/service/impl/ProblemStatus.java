package com.sfacl.magnus.service.impl;

import com.sfacl.magnus.entity.Problem;
import com.sfacl.magnus.repository.ProblemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProblemStatus {


    private ProblemRepository problemRepository;

    public ProblemStatus(ProblemRepository problemRepository) {
        this.problemRepository = problemRepository;
    }

    public List<Problem> getSolvedProblems() {
        return problemRepository.findProblemsbyStatus(com.sfacl.magnus.entity.ProblemStatus.SOLVED);
    }

    public List<Problem> getUnsolvedSolvedProblems() {
        return problemRepository.findProblemsbyStatus(com.sfacl.magnus.entity.ProblemStatus.UNSOLVED);
    }


}
