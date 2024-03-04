package com.sfacl.magnus.controller;

import com.sfacl.magnus.entity.Problem;
import com.sfacl.magnus.entity.Sfacl;
import com.sfacl.magnus.entity.Staff;
import com.sfacl.magnus.service.ProblemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProblemController {


    private ProblemService problemService;



    public ProblemController(ProblemService problemService) {
        this.problemService = problemService;

    }

    @PostMapping("/problems")
    public ResponseEntity<String> createSfaclWithProblems(@RequestBody Sfacl sfacl) {

        problemService.createSfaclWithProblem(sfacl);
        return new ResponseEntity<>("Sfacl with problems created successfully", HttpStatus.CREATED);
    }

    @PostMapping("/staff-problems")
    public ResponseEntity<String> createStaffWithProblems(@RequestBody Staff staff){

        problemService.createStaffWithProblem(staff);
        return new ResponseEntity<>("Staff with problem created successfully",HttpStatus.CREATED);
    }



    @GetMapping("/problems")
    public ResponseEntity<List<Sfacl>> getAllSfacls() {
        List<Sfacl> sfacls = problemService.getAllSfacls();
        return new ResponseEntity<>(sfacls, HttpStatus.OK);
    }

    @GetMapping("/AllProblems")
    public ResponseEntity<List<Problem>> getAllProblems(){
        List<Problem> problems = problemService.getAllProblems();
        return new ResponseEntity<>(problems,HttpStatus.OK);
    }


    @GetMapping("/staff-problems")
    public ResponseEntity<List<Staff>> gerAllStaff(){
        List<Staff> staffs = problemService.getAllStaff();
        return new ResponseEntity<>(staffs,HttpStatus.OK);
    }


    @GetMapping("/problems/{id}")
    public ResponseEntity<Problem> getProblemById(@PathVariable Long id) {
        Problem problem = problemService.getProblemById(id);
        if (problem != null) {
            return new ResponseEntity<>(problem, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/problems/{id}")
    public ResponseEntity<String> updateProblem(@PathVariable Long id, @RequestBody Problem problem) {
        String result = problemService.updateProblem(id, problem);
        if (result.equals("Problem updated successfully")) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/problems/{id}")
    public ResponseEntity<String> deleteProblem(@PathVariable Long id) {
        String result = problemService.deleteProblem(id);
        if (result.equals("Problem deleted successfully")) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
    }
}
