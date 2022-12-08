package com.pokemon.studi.repository;

import com.pokemon.studi.pojo.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon,Long> {



}
