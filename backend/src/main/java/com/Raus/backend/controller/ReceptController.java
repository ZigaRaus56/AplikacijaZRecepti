package com.Raus.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag (name = "Recepti", description = "CRUD operacije")
@RestController
@RequestMapping("/api/recepti")
@CrossOrigin(origins = "http://localhost:5173")
public class ReceptController {

    @Autowired 
    private ReceptRepository rep;

    @Operation (summary = "Vrne vse recepte")
    @GetMapping
    public List<Recept> getAll() {
        return rep.findAll();
    }

    @Operation (summary = "Vrne en recept po idju")
    @ApiResponse (responseCode = "200", description = "Recept najden")
    @ApiResponse (responseCode = "404", description = "Recept ni najden")
    @GetMapping("/{id}")    
    public Optional<Recept> getOne(@PathVariable Long id) {
        return rep.findById(id);
    }

    @Operation (summary = "Ustvari nov recept")
    @ApiResponse (responseCode= "201", description="Recept ustvarjen")
    @ApiResponse (responseCode="400", description="Napaka")
    @PostMapping
    public Recept create(@RequestBody Recept r) {
        return rep.save(r);
    }

    @Operation (summary = "Posodobi recept")
    @ApiResponse (responseCode= "200", description="Recept posodobljen")
    @ApiResponse (responseCode="404", description="Recept ne obstaja")
    @PutMapping("/{id}")
    public Recept update(@PathVariable Long id, @RequestBody Recept r) {
        r.setId(id);
        return rep.save(r);
    }

    @Operation (summary = "Odstrani recept")
    @ApiResponse (responseCode= "204", description="Recept izbrisan")
    @ApiResponse (responseCode="404", description="Recept ne obstaja")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        rep.deleteById(id);
    }
}