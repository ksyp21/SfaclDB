package com.sfacl.magnus.service.impl;

import com.sfacl.magnus.entity.District;
import com.sfacl.magnus.repository.DistrictRepository;
import com.sfacl.magnus.service.DistrictService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DistrictServiceImpl implements DistrictService {


    private DistrictRepository districtRepository;

    public DistrictServiceImpl(DistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
    }

    @Override
    public void createDistrict(District district) {
        districtRepository.save(district);
    }

    @Override
    public List<District> getAllDistricts() {
        return districtRepository.findAll();
    }

    @Override
    public District getDistrictById(Long id) {
        Optional<District> optionalDistrict = districtRepository.findById(id);
        return optionalDistrict.orElse(null);
    }


    @Override
    public String updateDistrict(Long id, District district) {
        Optional<District> d= districtRepository.findById(id);
        if (d.isPresent()) {
            District existingDistrict = d.get();
            existingDistrict.setDistrictName(district.getDistrictName());
            districtRepository.save(existingDistrict);
            return "District updated successfully";
        }
        return "District not found";
    }

    @Override
    public String deleteDistrict(Long id) {
        if (districtRepository.existsById(id)) {
            districtRepository.deleteById(id);
            return "District deleted successfully";
        }
        return "District not found";
    }
}
