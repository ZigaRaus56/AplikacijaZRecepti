package com.Raus.backend.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
@JsonPropertyOrder({"id", "naslov", "opis", "sestavine", "navodila","casKuhanja", "porcije", "kategorija"})

public class Recept {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String naslov;
    private String opis;
    @OneToMany(cascade= CascadeType.ALL, orphanRemoval=true, fetch=FetchType.EAGER)
    @JoinColumn (name = "recept_id")
    private List<Sestavina> sestavine = new ArrayList<>();

    private String navodila;

    private int casKuhanja;
    private int porcije;

    @ManyToOne 
    @JoinColumn(name = "kategorija_id")
    private Kategorija kategorija;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNaslov() { return naslov; }
    public void setNaslov(String naslov) { this.naslov = naslov; }

    public String getOpis() { return opis; }
    public void setOpis(String opis) { this.opis = opis; }

    public List<Sestavina> getSestavine(){
        return sestavine;
    }
    public void setSestavine(List<Sestavina> sestavine){
        this.sestavine = sestavine;
    }

    public String getNavodila() { return navodila; }
    public void setNavodila(String navodila) { this.navodila = navodila; }

    public int getCasKuhanja() { return casKuhanja; }
    public void setCasKuhanja(int casKuhanja) { this.casKuhanja = casKuhanja; }

    public int getPorcije() { return porcije; }
    public void setPorcije(int porcije) { this.porcije = porcije; }

    public Kategorija getKategorija() { return kategorija; }
    public void setKategorija(Kategorija kategorija) { this.kategorija = kategorija; }
}