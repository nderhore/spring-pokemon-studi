package com.pokemon.studi.repository;

import com.pokemon.studi.pojo.Capacite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CapaciteRepository extends JpaRepository<Capacite,Long> {
}
