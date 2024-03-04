package com.sfacl.magnus.service;

import com.sfacl.magnus.entity.Staff;

import java.util.List;
import java.util.Optional;

public interface StaffService {
    Staff createNewStaff(Staff staff,Long id);

    List<Staff> getAllStaff();
    Optional<Staff> getStaffById(long id);
    String updateById(long id, Staff staff);
    String deleteById(long id);
    String deleteAllStaff();

}
