package com.sfacl.magnus.repository;

import com.sfacl.magnus.entity.Problem;
import com.sfacl.magnus.entity.ProblemStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProblemRepository extends JpaRepository<Problem,Long> {
    @Query("SELECT p FROM Problem p WHERE p.status = :status")
    List<Problem> findProblemsbyStatus(@Param("status") ProblemStatus status);


}
