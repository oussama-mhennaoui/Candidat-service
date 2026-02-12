package com.esprit.microservice.candidat.controller;

import com.esprit.microservice.candidat.model.Candidat;
import com.esprit.microservice.candidat.service.CandidatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/candidats")
public class CandidatRestAPI {

    @Autowired
    private CandidatService candidatService;

    // GET /candidats/hello
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, i'm the candidate Micro-Service";
    }

    // GET /candidats - Récupérer tous les candidats
    @GetMapping
    public ResponseEntity<List<Candidat>> getAllCandidats() {
        List<Candidat> candidats = candidatService.getAll();
        if (candidats.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(candidats);
    }

    // GET /candidats/{id} - Récupérer un candidat par ID
    @GetMapping("/{id}")
    public ResponseEntity<Candidat> getCandidatById(@PathVariable int id) {
        Candidat candidat = candidatService.getCandidatById(id);
        if (candidat != null) {
            return ResponseEntity.ok(candidat);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // POST /candidats - Ajouter un nouveau candidat
    @PostMapping
    public ResponseEntity<Candidat> addCandidat(@RequestBody Candidat candidat) {
        Candidat newCandidat = candidatService.addCandidat(candidat);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCandidat);
    }

    // PUT /candidats/{id} - Mettre à jour un candidat
    @PutMapping("/{id}")
    public ResponseEntity<Candidat> updateCandidat(@PathVariable int id, @RequestBody Candidat candidat) {
        Candidat updatedCandidat = candidatService.updateCandidat(id, candidat);
        if (updatedCandidat != null) {
            return ResponseEntity.ok(updatedCandidat);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /candidats/{id} - Supprimer un candidat
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCandidat(@PathVariable int id) {
        String result = candidatService.deleteCandidat(id);
        if (result.equals("Candidat supprimé avec succès")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
    }

    // GET /candidats/count - Compter les candidats
    @GetMapping("/count")
    public ResponseEntity<Long> countCandidats() {
        long count = candidatService.countCandidats();
        return ResponseEntity.ok(count);
    }
}
