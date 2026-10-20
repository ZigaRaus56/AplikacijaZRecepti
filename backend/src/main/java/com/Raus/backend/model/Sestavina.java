package com.Raus.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity 
public class Sestavina {

    @Id 
    @GeneratedValue (strategy= GenerationType.IDENTITY)

    private Long id;
    private String naziv;
    private double kolicina;
    private String enota;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNaziv(){
        return naziv;
    }
    public void setNaziv(String naziv){
        this.naziv = naziv;
    }
    public double getKolicina(){
        return kolicina;
    }
    public void setKolicina(double kolicina){
        this.kolicina = kolicina;
    }
    public String getEnota(){
        return enota;
    }
    public void setEnota(String enota){
        this.enota = enota;
    }
}
