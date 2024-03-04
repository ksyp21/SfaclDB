package com.sfacl.magnus.service;

import com.sfacl.magnus.entity.Sfacl;

import java.util.List;
import java.util.Optional;

public interface SfaclService {
    List<Sfacl> getSfacl();
    Optional<Sfacl> getSfaclById(long id);
    void createNewSfacl(Sfacl sfacl);
    void updateSfaclById(Long id, Sfacl sfacl);
    void deleteSfaclById(Long id);
    void deleteAllSfacl();
}
