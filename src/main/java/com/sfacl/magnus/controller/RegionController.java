package com.sfacl.magnus.controller;


import com.sfacl.magnus.entity.Region;
import com.sfacl.magnus.service.RegionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/regions")
public class RegionController {


    private RegionService regionService;

    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    @PostMapping
    public ResponseEntity<String> createRegion(@RequestBody Region region) {
        regionService.createNewRegion(region);
        return ResponseEntity.status(HttpStatus.CREATED).body("Region created successfully");
    }

    @GetMapping
    public ResponseEntity<List<Region>> getAllRegions() {
        List<Region> regions = regionService.getAllRegion();
        return ResponseEntity.ok(regions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Region> getRegionById(@PathVariable long id) {
        Optional<Region> region = regionService.getRegionById(id);
        return region.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateRegionById(@RequestBody Region region, @PathVariable long id) {
        String message = regionService.updateRegionById(region, id);
        if (message.startsWith("Value updated")) {
            return ResponseEntity.ok(message);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRegionById(@PathVariable long id) {
        String message = regionService.deleteRegionById(id);
        if (message.startsWith("Value for")) {
            return ResponseEntity.ok(message);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllRegions() {
        String message = regionService.deleteAllRegion();
        return ResponseEntity.ok(message);
    }

    //dto


}
