package com.Raus.backend.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@JsonPropertyOrder({"id", "naslov", "opis", "sestavine", "navodila","casKuhanja", "porcije", "kategorija"})

public class Recept {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String naslov;
    private String opis;
    private String sestavine;
    private String navodila;

    private int casKuhanja;
    private int porcije;
    private String kategorija;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNaslov() { return naslov; }
    public void setNaslov(String naslov) { this.naslov = naslov; }

    public String getOpis() { return opis; }
    public void setOpis(String opis) { this.opis = opis; }

    public String getSestavine() { return sestavine; }
    public void setSestavine(String sestavine) { this.sestavine = sestavine; }

    public String getNavodila() { return navodila; }
    public void setNavodila(String navodila) { this.navodila = navodila; }

    public int getCasKuhanja() { return casKuhanja; }
    public void setCasKuhanja(int casKuhanja) { this.casKuhanja = casKuhanja; }

    public int getPorcije() { return porcije; }
    public void setPorcije(int porcije) { this.porcije = porcije; }

    public String getKategorija() { return kategorija; }
    public void setKategorija(String kategorija) { this.kategorija = kategorija; }
}