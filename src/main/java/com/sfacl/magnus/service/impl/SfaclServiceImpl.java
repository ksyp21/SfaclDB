package com.sfacl.magnus.service.impl;

import com.sfacl.magnus.entity.Sfacl;
import com.sfacl.magnus.repository.SfaclRepository;
import com.sfacl.magnus.service.SfaclService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SfaclServiceImpl implements SfaclService {


    private SfaclRepository sfaclRepository;

    public SfaclServiceImpl(SfaclRepository sfaclRepository) {
        this.sfaclRepository = sfaclRepository;
    }

    @Override
    public List<Sfacl> getSfacl() {
        return sfaclRepository.findAll();
    }

    @Override
    public Optional<Sfacl> getSfaclById(long id) {
        return sfaclRepository.findById(id);
    }

    @Override
    public void createNewSfacl(Sfacl sfacl) {
        sfaclRepository.save(sfacl);

    }

    @Override
    public void updateSfaclById(Long id, Sfacl sfacl) {
        Optional<Sfacl>existingSfacl=sfaclRepository.findById(id);
        existingSfacl.ifPresentOrElse(
                s ->{
                    s.setName(sfacl.getName());
                    sfaclRepository.save(s);
                },
                ()->{
                    throw new IllegalArgumentException("sfacl with "+id+ " not found");
                }
        );


    }

    @Override
    public void deleteSfaclById(Long id) {
        sfaclRepository.deleteById(id);

    }

    @Override
    public void deleteAllSfacl() {
        sfaclRepository.deleteAll();

    }
}
