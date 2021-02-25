package com.example.demo.service.Cycle;

import com.example.demo.modele.Cycle;
import com.example.demo.repository.CycleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CycleServiceImpl implements CycleIService {

    @Autowired
    public CycleRepository cycleRepository;

    @Override
    public List<Cycle> listCycles() {
        return cycleRepository.findAll();
    }
}
