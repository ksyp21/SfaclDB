package com.sfacl.magnus.service;

import com.sfacl.magnus.entity.Address;

import java.util.List;
import java.util.Optional;

public interface AddressService {
    String createNewAddress(Address address);
    List<Address> getAllAddress();
    Optional<Address> getAddressById(long id);
    String updateById(long id, Address address);
    String deleteById(long id);
    String deleteAllAddress();
}
