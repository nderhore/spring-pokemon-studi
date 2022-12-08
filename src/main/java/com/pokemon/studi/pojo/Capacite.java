package com.pokemon.studi.pojo;

import javax.persistence.*;

@Entity
public class Capacite {

    @Id
    @SequenceGenerator(name="capacite_seq",
            sequenceName = "capacite_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "capacite_seq")
    @Column(name="capacite_id",updatable = false)
    private Long id;

    private String libelle;

    private Long puissance;

    @ManyToOne
    @JoinColumn(name="pokemon_id",nullable = false)
    private Pokemon pokemon;

    public Capacite(Long id, String libelle, Long puissance) {
        this.id = id;
        this.libelle = libelle;
        this.puissance = puissance;
    }

    public Capacite(String libelle, Long puissance) {
        this.libelle = libelle;
        this.puissance = puissance;
    }

    public Capacite() {
        //Nedded by hibernate
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Long getPuissance() {
        return puissance;
    }

    public void setPuissance(Long puissance) {
        this.puissance = puissance;
    }

    }
