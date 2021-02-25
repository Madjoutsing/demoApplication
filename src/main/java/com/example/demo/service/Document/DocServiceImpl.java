package com.example.demo.service.Document;

import com.example.demo.modele.Document;
import com.example.demo.modele.ElasticSearch.ModeleDoc;
import com.example.demo.repository.DocumentRepository;
import com.example.demo.repository.ElasticSearch.RepositoryDoc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocServiceImpl implements DocIService {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private RepositoryDoc repositoryDoc;

    @Override
    public void saveDoc(Document document) {

        documentRepository.save(document);
        ModeleDoc m = new ModeleDoc(document.getIdDoc(),document.getTheme() + " " + document.getNomEntreprise()+ " " + document.getTitre() + " " + document.getEtudiant() + " " + document.getDateSoutenance() + " " + document.getDate(),document.getPathFile(),document.getDateScolarite());
        repositoryDoc.save(m);
    }

    @Override
    public List<Document> findByTheme(String theme) {
        return documentRepository.findByTheme(theme);
    }


    public void saveDocuments(){
//        ModeleDoc m = new ModeleDoc(1L, "Gestion électronique des rapports de stage","", null);
//        ModeleDoc m1 = new ModeleDoc(2L, "Gestion du parc informatique","", null);
//        repositoryDoc.saveAll(Arrays.asList(m,m1));
    }

    public List<ModeleDoc> search(String s){
        return repositoryDoc.findAllBySearch(s);
    }


    @Override
    public List<Document> allDocuments() {
        return documentRepository.findAll();
    }

    @Override
    public void deleteDocument(String idDoc) {

    }

    @Override
    public List<ModeleDoc> rechercheDocument(String search) {
        return repositoryDoc.findAllBySearch(search);
    }

    @Override
    public Document saveAll (Document document) {

        documentRepository.save(document);
        ModeleDoc m = new ModeleDoc(document.getIdDoc(),document.getTheme() + " " + document.getNomEntreprise()+ " " + document.getTitre() + " " + document.getEtudiant().getNom() + " " + document.getDateSoutenance() + " " + document.getDate() + " " + document.getMotCle(),document.getPathFile(),document.getDateScolarite());
          repositoryDoc.save(m);
          return document;
    }

    @Override
    public List<Document> findAll (Document document){
        return documentRepository.findAll();
    }

    @Override
    public void deleteById (Long idDoc){
        documentRepository.deleteById(idDoc);
    }

    @Override
    public List<Document> findByIdDoc(Long idDoc) {
        return documentRepository.findByIdDoc(idDoc);
    }

    @Override
    public List<Document> findByMotCle(String motCle) {
        return documentRepository.findByMotCle(motCle);
    }

}
