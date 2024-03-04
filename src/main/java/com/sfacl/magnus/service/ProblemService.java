package com.sfacl.magnus.service;


import com.sfacl.magnus.entity.Problem;
import com.sfacl.magnus.entity.Sfacl;
import com.sfacl.magnus.entity.Staff;

import java.util.List;

public interface ProblemService {



    void createSfaclWithProblem(Sfacl sfacl);

    void createStaffWithProblem(Staff staff);

//    void createProblemWithLof(ProblemLog problemLog);


    List<Sfacl> getAllSfacls();

    List<Staff> getAllStaff();

    List<Problem> getAllProblems();

//    List<ProblemLog> getAllProblemLog();

    Problem getProblemById(Long id);

    String updateProblem(Long id, Problem problem);

    String deleteProblem(Long id);
}
