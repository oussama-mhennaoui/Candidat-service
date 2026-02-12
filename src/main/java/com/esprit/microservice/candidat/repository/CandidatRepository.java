package com.esprit.microservice.candidat.repository;

import com.esprit.microservice.candidat.model.Candidat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CandidatRepository extends JpaRepository<Candidat, Integer> {
    // Recherche par nom (avec LIKE pour la recherche partielle)
    @Query("SELECT c FROM Candidat c WHERE c.nom LIKE %:name%")
    Page<Candidat> candidatByNom(@Param("name") String name, Pageable pageable);
}
