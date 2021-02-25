package com.example.demo.service.Etudiant;

import com.example.demo.modele.Etudiant;
import com.example.demo.repository.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EtudiantServiceImpl implements EtudiantIService {
    @Autowired //injection de l'interface Repository
    private EtudiantRepository etudiantRepository;

    @Override
    public Etudiant enregistrerEtudiant(Etudiant etudiant) {
        return etudiantRepository.save(etudiant);
    }

    @Override
    public List<Etudiant> listEtudiantService() {
        return null;
    }

    @Override
    public List<Etudiant> findByNom(String nom) {
        return null;
    }

    @Override
    public void deleteEtudiant(String id) {

    }

    @Override
    public Etudiant findBymail (String mail) {
        return etudiantRepository.findByMail(mail);
    };

    @Override
    public Etudiant findBynumero (Long numero) {
        return etudiantRepository.findByNumero(numero);
    }



}
