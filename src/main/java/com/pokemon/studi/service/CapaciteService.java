package com.pokemon.studi.service;

import com.pokemon.studi.pojo.Capacite;

public interface CapaciteService {

    Capacite getCapaciteById(Long id);

    void createCapacite(Capacite capacite);
}
