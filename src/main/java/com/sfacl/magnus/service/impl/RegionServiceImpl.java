package com.sfacl.magnus.service.impl;


import com.sfacl.magnus.entity.Region;
import com.sfacl.magnus.repository.RegionRepository;
import com.sfacl.magnus.service.RegionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegionServiceImpl implements RegionService {

    private RegionRepository regionRepository;

    public RegionServiceImpl(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    @Override
    public void createNewRegion(Region region) {
        regionRepository.save(region);
    }

    @Override
    public List<Region> getAllRegion() {
        return regionRepository.findAll();
    }



    @Override
    public Optional<Region> getRegionById(long id) {
        return regionRepository.findById(id);
    }

    @Override
    public String updateRegionById(Region region, long id) {
        Optional<Region> r = regionRepository.findById(id);
       if (r.isPresent()){
           Region existingRegion = r.get();
           existingRegion.setRegionName(region.getRegionName());
           existingRegion.setProvinceName(region.getProvinceName());
           regionRepository.save(existingRegion);
           return"Value updated for "+id;

       }else return "Value does not exist";

    }

    @Override
    public String deleteRegionById(long id) {
        regionRepository.deleteById(id);
        return "Value for "+id+" deleted";
    }

    @Override
    public String deleteAllRegion() {
        regionRepository.deleteAll();
        return "Values deleted";
    }




}
