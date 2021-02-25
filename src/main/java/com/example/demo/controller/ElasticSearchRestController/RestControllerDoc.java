package com.example.demo.controller.ElasticSearchRestController;

import com.example.demo.modele.ElasticSearch.ModeleDoc;
import com.example.demo.service.ElasticSearch.IServiceDoc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestControllerDoc {
    @Autowired
    private IServiceDoc iServiceDoc;

    @GetMapping("/test")
    public void test(){
        iServiceDoc.saveDocuments();
    }

    @GetMapping("/searchtest/{query}")
    public List<ModeleDoc> Searchtest(@PathVariable String query){
        return iServiceDoc.search(query);
    }

}
