package com.example.demo.service.MembrePedagogie;

import com.example.demo.modele.Document;
import com.example.demo.modele.MembrePedagogie;

import java.util.List;
import java.util.Optional;

public interface MPIService {
    public MembrePedagogie enregistrerMembrePedagogie(MembrePedagogie membrePedagogie);
    public List<MembrePedagogie> findByNomM(String nom);
    public List<MembrePedagogie> listMembrePedagogieService();
    public void deleteMembrePedagogie (String id);
    public Document enregistrerDocumentService(MembrePedagogie membrePedagogie);
    public MembrePedagogie findBymail (String mail);
    public MembrePedagogie findBynumero (Long numero);
    public Optional<MembrePedagogie> rechercherId (Long id);
}
