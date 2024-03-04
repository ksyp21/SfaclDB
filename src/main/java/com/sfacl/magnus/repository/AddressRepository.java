package com.sfacl.magnus.repository;

import com.sfacl.magnus.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {
}
