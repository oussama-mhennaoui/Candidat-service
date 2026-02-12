package com.esprit.microservice.candidat;

import com.esprit.microservice.candidat.model.Candidat;
import com.esprit.microservice.candidat.repository.CandidatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class CandidatApplication {

    public static void main(String[] args) {
        SpringApplication.run(CandidatApplication.class, args);
    }

    @Autowired
    private CandidatRepository repository;

    @Bean
    ApplicationRunner init() {
        return args -> {
            // Vérifier si le repository est vide
            if (repository.count() == 0) {
                repository.save(new Candidat("Mariem", "Ch", "ma@esprit.tn"));
                repository.save(new Candidat("Sarra", "ab", "sa@esprit.tn"));
                repository.save(new Candidat("Mohamed", "ba", "mo@esprit.tn"));
                repository.save(new Candidat("Maroua", "dh", "maroua@esprit.tn"));
            }
            // Affichage
            repository.findAll().forEach(System.out::println);
        };
    }
}
