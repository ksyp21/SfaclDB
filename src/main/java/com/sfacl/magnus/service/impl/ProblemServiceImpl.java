package com.sfacl.magnus.service.impl;


import com.sfacl.magnus.entity.Problem;
import com.sfacl.magnus.entity.Sfacl;
import com.sfacl.magnus.entity.Staff;
import com.sfacl.magnus.repository.ProblemRepository;
import com.sfacl.magnus.repository.SfaclRepository;
import com.sfacl.magnus.repository.StaffRepository;
import com.sfacl.magnus.service.ProblemService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProblemServiceImpl implements ProblemService {


    private StaffRepository staffRepository;


    private SfaclRepository sfaclRepository;


    private ProblemRepository problemRepository;

    public ProblemServiceImpl(StaffRepository staffRepository, SfaclRepository sfaclRepository, ProblemRepository problemRepository) {
        this.staffRepository = staffRepository;
        this.sfaclRepository = sfaclRepository;
        this.problemRepository = problemRepository;
    }

    @Override
    public void createSfaclWithProblem(Sfacl sfacl) {
        sfaclRepository.save(sfacl);
    }

    @Override
    public void createStaffWithProblem(Staff staff) {
        staffRepository.save(staff);
    }


    @Override
    public List<Sfacl> getAllSfacls() {
        return sfaclRepository.findAll();
    }

    @Override
    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }

    @Override
    public List<Problem> getAllProblems() {
        return problemRepository.findAll();
    }

    @Override
    public Problem getProblemById(Long id) {
        Optional<Problem> optionalProblem = problemRepository.findById(id);
        return optionalProblem.orElse(null);
    }

    @Override
    public String updateProblem(Long id, Problem problem) {
        Optional<Problem> optionalProblem = problemRepository.findById(id);
        if (optionalProblem.isPresent()) {
            Problem existingProblem = optionalProblem.get();
            existingProblem.setProblemDescription(problem.getProblemDescription());
            existingProblem.setStatus(problem.getStatus());
            existingProblem.setSolutionDesctiption(problem.getSolutionDesctiption());
            problemRepository.save(problem);
            return "Problem updated successfully";
        }
        return "Problem not found";
    }

    @Override
    public String deleteProblem(Long id) {
        Optional<Problem> optionalProblem = problemRepository.findById(id);
        if (optionalProblem.isPresent()) {
            problemRepository.deleteById(id);
            return "Problem deleted successfully";
        }
        return "Problem not found";
    }
}
