package com.sfacl.magnus.controller;

import com.sfacl.magnus.entity.Sfacl;
import com.sfacl.magnus.service.SfaclService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class SfaclController {

    private SfaclService sfaclService;

    public SfaclController(SfaclService sfaclService) {
        this.sfaclService = sfaclService;
    }

    @PostMapping("/sfacl")
    public ResponseEntity<String> createNewSfacl(@RequestBody Sfacl sfacl){
        sfaclService.createNewSfacl(sfacl);
        return new ResponseEntity<>("Sfacl added to database",HttpStatus.CREATED);
    }

    @GetMapping("/sfacl")
    public ResponseEntity<List<Sfacl>> getAllSfacl(){  //response entity gives in json format
        List<Sfacl> sfaclList = sfaclService.getSfacl();
        return new ResponseEntity<>(sfaclList, HttpStatus.OK);
    }


    @GetMapping("/sfacl/{id}")
    public ResponseEntity<Sfacl> getSfaclById(@PathVariable long id) {
        Optional<Sfacl> sfacl = sfaclService.getSfaclById(id);
        return sfacl.map(value -> new ResponseEntity<>(value, HttpStatus.FOUND))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/sfacl/{id}")
    public ResponseEntity<String > updateSfaclById(@PathVariable Long id, @RequestBody Sfacl sfacl){
     sfaclService.updateSfaclById(id, sfacl);
     return new ResponseEntity<>("Value for "+id+" updated",HttpStatus.OK);


    }

    @DeleteMapping("/sfacl/{id}")
    public ResponseEntity<String > deleteSfaclById(@PathVariable Long id){
        sfaclService.deleteSfaclById(id);
        return new ResponseEntity<>("Value deleted for id "+id,HttpStatus.OK);
    }

    @DeleteMapping("/sfacl")
    public ResponseEntity<String> deleteSfacl(){
        sfaclService.deleteAllSfacl();
        return new ResponseEntity<>("Sfacl deleted",HttpStatus.OK);
    }

}
