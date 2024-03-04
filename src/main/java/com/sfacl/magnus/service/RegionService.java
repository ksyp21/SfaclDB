package com.sfacl.magnus.service;

import com.sfacl.magnus.entity.Region;


import java.util.List;
import java.util.Optional;

public interface RegionService {
    void createNewRegion(Region region);
    List<Region> getAllRegion();

    Optional<Region> getRegionById(long id);

    String updateRegionById(Region region,long id);

    String deleteRegionById(long id);
    String deleteAllRegion();


}
