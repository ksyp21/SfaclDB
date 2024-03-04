package com.sfacl.magnus.controller;

import com.sfacl.magnus.entity.Branch;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import com.sfacl.magnus.service.BranchService;


@RestController
@RequestMapping("/api")
public class BranchController {


    private BranchService branchService;

    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @PostMapping("/branch")
    public String createNewBranch(@RequestBody Branch branch) {
        return branchService.createNewBranch(branch);
    }

    @GetMapping("/branch")
    public ResponseEntity<List<Branch>> getAllBranch() {
        List<Branch> branchList = branchService.getAllBranch();
        return new ResponseEntity<>(branchList, HttpStatus.OK);
    }

    @GetMapping("/branch/{id}")
    public ResponseEntity<Branch> getBranchById(@PathVariable long id) {
        Optional<Branch> branch = branchService.getBranchById(id);
        return branch.map(value -> new ResponseEntity<>(value, HttpStatus.FOUND))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/branch/{id}")
    public String updateById(@PathVariable long id, @RequestBody Branch branch) {
        return branchService.updateById(id, branch);
    }

    @DeleteMapping("/branch/{id}")
    public String deleteById(@PathVariable long id) {
        return branchService.deleteBranchById(id);
    }

    @DeleteMapping("/branch")
    public String deleteAllBranch() {
        return branchService.deleteAllBranch();
    }
}
