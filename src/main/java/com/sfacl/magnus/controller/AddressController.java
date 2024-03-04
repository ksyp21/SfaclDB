package com.sfacl.magnus.controller;


import com.sfacl.magnus.entity.Address;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import com.sfacl.magnus.service.AddressService;



@RestController
@RequestMapping("/api")
public class AddressController {


    private AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("/address")
    public String createNewAddress(@RequestBody Address address) {
        return addressService.createNewAddress(address);
    }

    @GetMapping("/address")
    public ResponseEntity<List<Address>> getAllAddress() {
        List<Address> addressList = addressService.getAllAddress();
        return new ResponseEntity<>(addressList, HttpStatus.OK);
    }

    @GetMapping("/address/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable long id) {
        Optional<Address> address = addressService.getAddressById(id);
        return address.map(value -> new ResponseEntity<>(value, HttpStatus.FOUND))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/address/{id}")
    public String updateById(@PathVariable long id, @RequestBody Address address) {
        return addressService.updateById(id, address);
    }

    @DeleteMapping("/address/{id}")
    public String deleteById(@PathVariable long id) {
        return addressService.deleteById(id);
    }

    @DeleteMapping("/address")
    public String deleteAllAddress() {
        return addressService.deleteAllAddress();
    }
}

