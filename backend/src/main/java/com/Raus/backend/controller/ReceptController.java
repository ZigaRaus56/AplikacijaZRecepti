package com.Raus.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Raus.backend.model.Recept;
import com.Raus.backend.repository.ReceptRepository;

@RestController
@RequestMapping("/api/recepti")
public class ReceptController {

    @Autowired 
    private ReceptRepository rep;

    @GetMapping
    public List<Recept> getAll() {
        return rep.findAll();
    }

    @GetMapping("/{id}")    
    public Optional<Recept> getOne(@PathVariable Long id) {
        return rep.findById(id);
    }

    @PostMapping
    public Recept create(@RequestBody Recept r) {
        return rep.save(r);
    }

    @PutMapping("/{id}")
    public Recept update(@PathVariable Long id, @RequestBody Recept r) {
        r.setId(id);
        return rep.save(r);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        rep.deleteById(id);
    }
}