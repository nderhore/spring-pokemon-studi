package com.pokemon.studi.api;

import com.pokemon.studi.pojo.Pokemon;
import com.pokemon.studi.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiRegistration.REST_PREFIX + ApiRegistration.REST_POKEMON)
public class PokemonWs {


    @Autowired
    private PokemonService service;

    @GetMapping
    public List<Pokemon> getAllPokemon(){
        return service.getAllPokemon();
    }

    @GetMapping("{name}")
    public List<Pokemon> getAllPokemonByName(@PathVariable(name = "name") String nom){
        return service.getAllPokemonByName(nom);
    }
    @PostMapping
    public void createPokemon(@RequestBody Pokemon pokemon){
        service.createPokemon(pokemon);
    }

    @PutMapping("{name}")
    public void updatePokemon(@PathVariable(name = "name") String nom,
                              @RequestBody Pokemon pokemon){
        service.updatePokemon(nom,pokemon);
    }

    @DeleteMapping("{name}")
    public void deletePokemon(@PathVariable(name = "name") String nom){
        service.deletePokemon(nom);
    }
}
