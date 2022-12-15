package com.pokemon.studi.api;

import com.pokemon.studi.pojo.Pokemon;
import com.pokemon.studi.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(ApiRegistration.REST_PREFIX + ApiRegistration.REST_POKEMON)
public class PokemonWs {

    @Autowired
    private PokemonService service;

    @GetMapping
    public ModelAndView getAllPokemon(){
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("index.html");
    modelAndView.addObject("test","test sucess");
    return modelAndView;
        //return service.getAllPokemon();
    }

    @GetMapping("{id}")
    public Pokemon getPokemonById(@PathVariable(name = "id") Long id){

        return service.getPokemonById(id);
    }
    @PostMapping
    public void createPokemon(@RequestBody Pokemon pokemon){
        service.createPokemon(pokemon);
    }

    @PutMapping("{id}")
    public void updatePokemon(@PathVariable(name = "id") Long id,
                              @RequestBody Pokemon pokemon){
        service.updatePokemon(id,pokemon);
    }

    @DeleteMapping("{id}")
    public void deletePokemon(@PathVariable(name = "id") Long id){
        service.deletePokemon(id);
    }
}
