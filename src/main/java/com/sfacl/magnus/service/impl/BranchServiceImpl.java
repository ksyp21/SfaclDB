package com.sfacl.magnus.service.impl;

import com.sfacl.magnus.entity.Branch;
import com.sfacl.magnus.repository.BranchRepository;
import com.sfacl.magnus.service.BranchService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BranchServiceImpl implements BranchService {


    private BranchRepository branchRepository;

    public BranchServiceImpl(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    @Override
    public String createNewBranch(Branch branch) {
        branchRepository.save(branch);
        return "Branch added to database";
    }

    @Override
    public List<Branch> getAllBranch() {
        List<Branch> branchList = new ArrayList<>();
        branchRepository.findAll().forEach(branchList::add);
        return branchList;
    }

    @Override
    public Optional<Branch> getBranchById(long id) {
        return branchRepository.findById(id);
    }

    @Override
    public String updateById(long id, Branch branch) {
        Optional<Branch> b = branchRepository.findById(id);
        if (b.isPresent()) {
            Branch existingBranch = b.get();
            existingBranch.setAddress1(branch.getAddress1());
            existingBranch.setAddress2(branch.getAddress2());
            branchRepository.save(existingBranch);
            return "Value updated for " + id;
        } else {
            return "Value does not exist";
        }
    }

    @Override
    public String deleteBranchById(long id) {
        branchRepository.deleteById(id);
        return "Value deleted for " + id;
    }

    @Override
    public String deleteAllBranch() {
        branchRepository.deleteAll();
        return "Branch deleted";
    }
}
