package com.example.demo.service.FicheNotation;

import com.example.demo.modele.FicheNotation;

import java.util.List;

public interface FicheNotationIService {
    public void saveFiche(FicheNotation ficheNotation);
    public List<FicheNotation> findAll ();



}
