package com.esprit.microservice.candidat.service;

import com.esprit.microservice.candidat.model.Candidat;
import com.esprit.microservice.candidat.repository.CandidatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidatService {
    @Autowired
    private CandidatRepository candidatRepository;

    // Récupérer tous les candidats
    public List<Candidat> getAll() {
        return candidatRepository.findAll();
    }

    // Ajouter un candidat
    public Candidat addCandidat(Candidat candidat) {
        return candidatRepository.save(candidat);
    }

    // Mettre à jour un candidat
    public Candidat updateCandidat(int id, Candidat newCandidat) {
        if (candidatRepository.findById(id).isPresent()) {
            Candidat existingCandidat = candidatRepository.findById(id).get();
            existingCandidat.setNom(newCandidat.getNom());
            existingCandidat.setPrenom(newCandidat.getPrenom());
            existingCandidat.setEmail(newCandidat.getEmail());
            return candidatRepository.save(existingCandidat);
        } else {
            return null; // Ou lever une exception
        }
    }

    // Supprimer un candidat
    public String deleteCandidat(int id) {
        if (candidatRepository.findById(id).isPresent()) {
            candidatRepository.deleteById(id);
            return "Candidat supprimé avec succès";
        } else {
            return "Candidat non trouvé";
        }
    }

    // Récupérer un candidat par ID
    public Candidat getCandidatById(int id) {
        return candidatRepository.findById(id).orElse(null);
    }
    public long countCandidats() {
        return candidatRepository.count();
    }

}
