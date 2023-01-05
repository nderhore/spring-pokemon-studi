package com.pokemon.studi.api;

import com.pokemon.studi.pojo.Capacite;
import com.pokemon.studi.service.CapaciteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiRegistration.REST_PREFIX + ApiRegistration.REST_CAPACITE)
public class CapaciteWs {

    @Autowired
    private CapaciteService capaciteService;

    @GetMapping("{id}")
    public Capacite getCapaciteById(@PathVariable(name = "id") Long id){

        return capaciteService.getCapaciteById(id);
    }

    @PostMapping
    public void createCapacite(@RequestBody Capacite capacite){
        capaciteService.createCapacite(capacite);
    }
}
