package com.example.demo.repository;

import com.example.demo.modele.Filiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface FiliereRepository extends JpaRepository<Filiere, Long> {
}
