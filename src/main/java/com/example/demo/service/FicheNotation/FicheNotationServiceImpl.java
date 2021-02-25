package com.example.demo.service.FicheNotation;

import com.example.demo.modele.FicheNotation;
import com.example.demo.repository.FicheNotationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FicheNotationServiceImpl implements FicheNotationIService {

    @Autowired
    FicheNotationRepository ficheNotationRepository;

    @Override
    public void saveFiche(FicheNotation ficheNotation) {

    }


    @Override
    public List<FicheNotation> findAll() {
        return ficheNotationRepository.findAll();
    }




}
