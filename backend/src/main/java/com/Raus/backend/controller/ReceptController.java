package com.Raus.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Raus.backend.model.Recept;
import com.Raus.backend.model.Zahtevnost;
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

    @GetMapping
    @Operation (summary = "Vrne recepte z iskanjem in filtri")
    public List<Recept> getAll(
        @RequestParam (required=false) String iskanje,
        @RequestParam (required=false) Long kategorijaId,
        @RequestParam (required=false) Integer maxCas,
        @RequestParam (required=false) Zahtevnost zahtevnost) {

        Specification<Recept> spec = (root, query, cb) -> cb.conjunction();

        if (iskanje != null && !iskanje.isBlank()) {
            spec = spec.and((root, query, cb) ->
                cb.like(cb.lower(root.<String>get("naslov")), "%" + iskanje.trim().toLowerCase() + "%"));
        }
        if (kategorijaId != null) {
            spec = spec.and((root, query, cb) ->
                cb.equal(root.get("kategorija").get("id"), kategorijaId));
        }
        if (maxCas != null) {
            spec = spec.and((root, query, cb) ->
                cb.lessThanOrEqualTo(root.<Integer>get("casKuhanja"), maxCas));
        }
        if (zahtevnost != null) {
            spec = spec.and((root, query, cb) ->
                cb.equal(root.get("zahtevnost"), zahtevnost));
        }

        return rep.findAll(spec);
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