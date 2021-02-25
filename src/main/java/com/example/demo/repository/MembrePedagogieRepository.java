package com.example.demo.repository;

import com.example.demo.modele.MembrePedagogie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface MembrePedagogieRepository extends JpaRepository<MembrePedagogie, Long> {
    public List<MembrePedagogie> findByNom(String nom);
    public void deleteById (String id);
    public MembrePedagogie findById (int id);
    public MembrePedagogie findByMail (String mail);
    public MembrePedagogie findByNumero (Long numero);

    @Query("SELECT m FROM  MembrePedagogie m WHERE m.id=?1")
    public MembrePedagogie rechercherById(Long id);
}
