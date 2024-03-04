package com.sfacl.magnus.controller;


import com.sfacl.magnus.entity.Staff;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import com.sfacl.magnus.service.StaffService;


@RestController
@RequestMapping("/api")
public class StaffController {


    private StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }


    @PostMapping("/staff/{id}")
    public ResponseEntity<String> createNewStaff(@RequestBody Staff staff, @PathVariable Long id) {
        Staff createdStaff = staffService.createNewStaff(staff,id);
        if(createdStaff!=null){
            return new ResponseEntity<>("Staff created successfully",HttpStatus.CREATED);

        }else {
            return new ResponseEntity<>("Failed to create staff. Category not found",HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/staff")
    public ResponseEntity<List<Staff>> getAllStaff() {
        List<Staff> staffList = staffService.getAllStaff();
        return new ResponseEntity<>(staffList, HttpStatus.OK);
    }

    @GetMapping("/staff/{id}")
    public ResponseEntity<Staff> getStaffById(@PathVariable long id) {
        Optional<Staff> staff = staffService.getStaffById(id);
        return staff.map(value -> new ResponseEntity<>(value, HttpStatus.FOUND))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/staff/{id}")
    public String updateById(@PathVariable long id, @RequestBody Staff staff) {
        return staffService.updateById(id, staff);
    }

    @DeleteMapping("/staff/{id}")
    public String deleteById(@PathVariable long id) {
        return staffService.deleteById(id);
    }

    @DeleteMapping("/staff")
    public String deleteAllStaff() {
        return staffService.deleteAllStaff();
    }
}
