package com.example.demo.service.Filiere;

import com.example.demo.modele.Filiere;
import com.example.demo.repository.FiliereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FiliereServiceImpl implements FiliereIService {

    @Autowired
    public FiliereRepository filiereRepository;

    @Override
    public List<Filiere> listFilieres() {return filiereRepository.findAll();}

}
