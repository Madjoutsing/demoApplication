package com.example.demo.repository;


import com.example.demo.modele.FicheNotation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
@Repository
public interface FicheNotationRepository  extends JpaRepository<FicheNotation, Long>{
    List<FicheNotation> findAll();
}
