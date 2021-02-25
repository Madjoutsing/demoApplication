package com.example.demo.repository;

import com.example.demo.modele.Cycle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface CycleRepository extends JpaRepository<Cycle, Long> {
}
