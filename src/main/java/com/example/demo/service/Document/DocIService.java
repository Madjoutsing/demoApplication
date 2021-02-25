package com.example.demo.service.Document;

import com.example.demo.modele.Document;
import com.example.demo.modele.ElasticSearch.ModeleDoc;

import java.util.List;

public interface DocIService {
    public void saveDoc(Document document);
    public List<Document> findByTheme(String theme);
    public List<Document> allDocuments();
    public void deleteDocument (String idDoc);
    public List<ModeleDoc> rechercheDocument (String search);
    public Document saveAll(Document document);
    public List<Document> findAll (Document document);
    public void deleteById (Long idDoc);
    public List<Document> findByIdDoc (Long idDoc);
    public List<Document> findByMotCle (String motCle);


}
