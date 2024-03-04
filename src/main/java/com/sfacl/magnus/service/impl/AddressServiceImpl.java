package com.sfacl.magnus.service.impl;

import com.sfacl.magnus.entity.Address;
import com.sfacl.magnus.repository.AddressRepository;
import com.sfacl.magnus.service.AddressService;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {


    private AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }
    @Override
    public String createNewAddress(Address address) {
        addressRepository.save(address);
        return "Address added to database";
    }

    @Override
    public List<Address> getAllAddress() {
        List<Address> addressList = new ArrayList<>();
        addressRepository.findAll().forEach(addressList::add);
        return addressList;
    }

    @Override
    public Optional<Address> getAddressById(long id) {
        return addressRepository.findById(id);
    }

    @Override
    public String updateById(long id, Address address) {
        Optional<Address> a = addressRepository.findById(id);
        if (a.isPresent()) {
            Address existingAddress = a.get();
            existingAddress.setAddress1(address.getAddress1());
            existingAddress.setAddress2(address.getAddress2());
            existingAddress.setDistrictName(address.getDistrictName());
            existingAddress.setPostalCode(address.getPostalCode());
            addressRepository.save(existingAddress);
            return "Value updated for " + id;
        } else {
            return "Value does not exist";
        }
    }

    @Override
    public String deleteById(long id) {
        addressRepository.deleteById(id);
        return "Value deleted for " + id;
    }

    @Override
    public String deleteAllAddress() {
        addressRepository.deleteAll();
        return "Address deleted";
    }
}
