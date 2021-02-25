package com.example.demo.repository;

import com.example.demo.modele.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
    public List<Etudiant> findByNom(String nom);
    public void deleteById(String id);
    public Etudiant findById(int id);
    public Etudiant findByMail (String mail);
    public Etudiant findByNumero (Long numero);

    @Query("SELECT e FROM  Etudiant e WHERE e.id=?1")
    public Etudiant rechercherById(Long id);
}
