package com.pokemon.studi.service.impl;

import com.pokemon.studi.pojo.Capacite;
import com.pokemon.studi.repository.CapaciteRepository;
import com.pokemon.studi.service.CapaciteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CapaciteServiceImpl implements CapaciteService {

    @Autowired
    private CapaciteRepository capaciteRepository;


    @Override
    public Capacite getCapaciteById(Long id) {
        return capaciteRepository.findById(id).orElse(null);
    }

    @Override
    public void createCapacite(Capacite capacite) {
        capaciteRepository.save(capacite);
    }
}
