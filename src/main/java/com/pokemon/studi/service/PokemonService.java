package com.pokemon.studi.service;

import com.pokemon.studi.pojo.Pokemon;

import java.util.List;

public interface PokemonService {

    List<Pokemon> getAllPokemon();

    Pokemon getPokemonById(Long id);

    void updatePokemon(Long id, Pokemon pokemon);

    void createPokemon(Pokemon pokemon);

    void deletePokemon(Long id);
}
