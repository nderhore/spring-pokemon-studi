package com.pokemon.studi.service.impl;

import com.pokemon.studi.pojo.Pokemon;
import com.pokemon.studi.repository.PokemonRepository;
import com.pokemon.studi.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PokemonServiceImpl implements PokemonService {

    @Autowired
    private PokemonRepository pokemonRepository;

    @Override
    public List<Pokemon> getAllPokemon() {
        return pokemonRepository.findAll();
    }

    @Override
    public Pokemon getPokemonById(Long id) {
            if(verifId(id)) {
                Optional<Pokemon> pokemonOptional = pokemonRepository.findById(id);
                return pokemonOptional.orElse(null);
            }
            else {
                return null;
            }
    }

    @Override
    public void updatePokemon(Long id, Pokemon pokemon) {
        this.deletePokemon(id);
        pokemonRepository.save(pokemon);
    }

    @Override
    public void createPokemon(Pokemon pokemon) {
        pokemonRepository.save(pokemon);
    }

    @Override
    public void deletePokemon(Long id) {
        pokemonRepository.deleteById(id);
    }

    private boolean verifId(Long id){
        return id != 0; //return True si id != 0 sinon, false
    }
}
