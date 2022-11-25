package com.pokemon.studi.api;

import com.pokemon.studi.pojo.Pokemon;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiRegistration.REST_PREFIX + ApiRegistration.REST_POKEMON)
public class PokemonWs {


    @GetMapping
    public List<Pokemon> getAllPokemon(){
        return null;
    }

    @PostMapping
    public void createPokemon(@RequestBody Pokemon pokemon){

    }

    @PutMapping("{name}")
    public void updatePokemon(@PathVariable(name = "name") String nom){

    }

    @DeleteMapping("{name}")
    public void deletePokemon(@PathVariable(name = "name") String nom){

    }
}
