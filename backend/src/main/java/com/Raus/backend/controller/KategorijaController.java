package com.Raus.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Raus.backend.model.Kategorija;
import com.Raus.backend.repository.KategorijaRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag (name="Kategorije", description="Upravljanje")
@CrossOrigin(origins = "http://localhost:5173")



@RestController 
@RequestMapping("/api/kategorije")

public class KategorijaController {
    
    @Autowired 
    private KategorijaRepository rep;

    @Operation (summary="Vrne vse kategorije")
    @GetMapping 
    public List<Kategorija> getAll(){
        return rep.findAll();
    }
    @Operation (summary="Ustvari kategorijo")
    @PostMapping 
    public Kategorija create(@RequestBody Kategorija k){
        return rep.save(k);
    }
    
    
}
