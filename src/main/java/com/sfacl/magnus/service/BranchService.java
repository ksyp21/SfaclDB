package com.sfacl.magnus.service;

import com.sfacl.magnus.entity.Branch;

import java.util.List;
import java.util.Optional;

public interface BranchService {
    String createNewBranch(Branch branch);
    List<Branch> getAllBranch();
    Optional<Branch> getBranchById(long id);
    String updateById(long id, Branch branch);
    String deleteBranchById(long id);
    String deleteAllBranch();
}
