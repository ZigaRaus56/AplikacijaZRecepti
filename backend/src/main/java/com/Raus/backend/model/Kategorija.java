package com.Raus.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity

public class Kategorija {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Long id;
    private String ime;

    public long getId(){
        return id;
    }
    public void setId(long id){
        this.id=id;
    }
    public String getIme(){
        return ime;
    }
    public void setIme(String ime){
        this.ime = ime;
    }
    
}
