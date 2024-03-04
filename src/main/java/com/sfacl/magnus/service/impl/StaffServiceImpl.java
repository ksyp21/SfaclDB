package com.sfacl.magnus.service.impl;


import com.sfacl.magnus.entity.Category;
import com.sfacl.magnus.entity.Staff;
import com.sfacl.magnus.repository.CategoryRepository;
import com.sfacl.magnus.repository.StaffRepository;
import com.sfacl.magnus.service.StaffService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StaffServiceImpl implements StaffService {


    private StaffRepository staffRepository;
    private CategoryRepository categoryRepository;




    public StaffServiceImpl(StaffRepository staffRepository, CategoryRepository categoryRepository) {
        this.staffRepository = staffRepository;
        this.categoryRepository=categoryRepository;

    }

    @Override
    public List<Staff> getAllStaff() {
        List<Staff> staffList = new ArrayList<>();
        staffRepository.findAll().forEach(staffList::add);
        return staffList;
    }

    @Override
    public Staff createNewStaff(Staff staff,Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if(optionalCategory.isPresent()){
            Category category = optionalCategory.get();

            staff.setCategory(category);

            return staffRepository.save(staff);
        }else {
            return null;
        }


    }

    @Override
    public Optional<Staff> getStaffById(long id) {
        return staffRepository.findById(id);
    }

    @Override
    public String updateById(long id, Staff staff) {
        Optional<Staff> s = staffRepository.findById(id);
        if (s.isPresent()) {
            Staff existingStaff = s.get();
            existingStaff.setFirstname(staff.getFirstname());
            existingStaff.setMiddlename(staff.getMiddlename());
            existingStaff.setLastname(staff.getLastname());
            existingStaff.setPhonenumber(staff.getPhonenumber());
            staffRepository.save(existingStaff);
            return "Value updated for " + id;
        } else {
            return "Value does not exist";
        }
    }

    @Override
    public String deleteById(long id) {
        staffRepository.deleteById(id);
        return "Value deleted for " + id;
    }

    @Override
    public String deleteAllStaff() {
        staffRepository.deleteAll();
        return "Staffs deleted";
    }


}
