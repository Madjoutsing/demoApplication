package com.example.demo.service.Etudiant;

import com.example.demo.modele.Etudiant;

import java.util.List;

public interface EtudiantIService {
    public Etudiant enregistrerEtudiant(Etudiant etudiant);
    public List<Etudiant> listEtudiantService();
    public List<Etudiant> findByNom(String nom);
    public void deleteEtudiant (String id);
    public Etudiant findBymail (String mail);
    public Etudiant findBynumero (Long numero);



}
