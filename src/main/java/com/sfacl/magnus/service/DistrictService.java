package com.sfacl.magnus.service;


import com.sfacl.magnus.entity.District;

import java.util.List;

public interface DistrictService {
    void createDistrict(District district);

    List<District> getAllDistricts();

    District getDistrictById(Long id);

    String updateDistrict(Long id, District district);

    String deleteDistrict(Long id);
}
