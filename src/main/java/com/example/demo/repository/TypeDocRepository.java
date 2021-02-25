package com.example.demo.repository;

import com.example.demo.modele.TypeDoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface TypeDocRepository extends JpaRepository<TypeDoc, Long> {
}
