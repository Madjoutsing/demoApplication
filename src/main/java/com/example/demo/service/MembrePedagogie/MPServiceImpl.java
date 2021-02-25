package com.example.demo.service.MembrePedagogie;

import com.example.demo.modele.Document;
import com.example.demo.modele.MembrePedagogie;
import com.example.demo.repository.MembrePedagogieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MPServiceImpl implements MPIService {

    @Autowired
    private MembrePedagogieRepository membrePedagogieRepository;

    @Override
    public MembrePedagogie enregistrerMembrePedagogie(MembrePedagogie membrePedagogie) {
        return membrePedagogieRepository.save(membrePedagogie);
    }

    @Override
    public List<MembrePedagogie> findByNomM(String nom) {
        return null;
    }

    @Override
    public List<MembrePedagogie> listMembrePedagogieService() {
        return null;
    }

    @Override
    public void deleteMembrePedagogie(String id) {

    }

    @Override
    public Document enregistrerDocumentService(MembrePedagogie membrePedagogie) {
        return null;
    }

    @Override
    public MembrePedagogie findBymail (String mail) {
        return membrePedagogieRepository.findByMail(mail);
    }

    @Override
    public MembrePedagogie findBynumero (Long numero) {
        return membrePedagogieRepository.findByNumero(numero);
    }

    @Override
    public Optional<MembrePedagogie> rechercherId(Long id) {
        return membrePedagogieRepository.findById(id);
    }
}
