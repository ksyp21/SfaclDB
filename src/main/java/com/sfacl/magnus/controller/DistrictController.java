package com.sfacl.magnus.controller;

import com.sfacl.magnus.entity.District;
import com.sfacl.magnus.service.DistrictService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/districts")
public class DistrictController {


    private DistrictService districtService;

    public DistrictController(DistrictService districtService) {
        this.districtService = districtService;
    }

    @PostMapping
    public ResponseEntity<String> createDistrict(@RequestBody District district) {
        districtService.createDistrict(district);
        return ResponseEntity.status(HttpStatus.CREATED).body("District created successfully");
    }

    @GetMapping
    public ResponseEntity<List<District>> getAllDistricts() {
        List<District> districts = districtService.getAllDistricts();
        return ResponseEntity.ok(districts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<District> getDistrictById(@PathVariable Long id) {
        District district = districtService.getDistrictById(id);
        if (district != null) {
            return ResponseEntity.ok(district);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateDistrict(@PathVariable Long id, @RequestBody District district) {
        String message = districtService.updateDistrict(id, district);
        if (message.equals("District updated successfully")) {
            return ResponseEntity.ok(message);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDistrict(@PathVariable Long id) {
        String message = districtService.deleteDistrict(id);
        if (message.equals("District deleted successfully")) {
            return ResponseEntity.ok(message);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }
}
