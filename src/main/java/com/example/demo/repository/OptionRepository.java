package com.example.demo.repository;


import com.example.demo.modele.OptionFiliere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface OptionRepository extends JpaRepository<OptionFiliere, Long> {

}
