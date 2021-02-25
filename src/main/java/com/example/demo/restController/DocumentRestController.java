package com.example.demo.restController;

import com.example.demo.modele.Document;
import com.example.demo.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class DocumentRestController {

    @Autowired
    private final DocumentRepository documentRepository;

    public DocumentRestController(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @GetMapping("documents")
    public List<Document> allDocuments(){
        List<Document> documents = documentRepository.findAll();

        return documents;
    }

}
