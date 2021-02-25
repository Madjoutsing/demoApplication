package com.example.demo.service.ElasticSearch;

import com.example.demo.modele.ElasticSearch.ModeleDoc;
import com.example.demo.repository.DocumentRepository;
import com.example.demo.repository.ElasticSearch.RepositoryDoc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IServiceDoc {

    @Autowired
    private RepositoryDoc repositoryDoc;

    @Autowired
    private DocumentRepository documentRepository;

    //test

    public void saveDocuments(){

    }

    public List<ModeleDoc> search(String s){
        return repositoryDoc.findAllBySearch(s);
    }
}
