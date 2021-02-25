package com.example.demo.repository;

import com.example.demo.modele.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    List<Document> findAll();
    List<Document> findByTheme(String theme);
    List<Document> findByIdDoc(Long id);
    List<Document> findByMotCle(String motCle);


//    @Query("SELECT d FROM Document d WHERE d.theme")
//    List<Document> findDivers(String theme);


}
