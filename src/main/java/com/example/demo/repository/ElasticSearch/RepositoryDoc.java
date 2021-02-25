package com.example.demo.repository.ElasticSearch;

import com.example.demo.modele.ElasticSearch.ModeleDoc;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryDoc extends ElasticsearchRepository <ModeleDoc, Long>{
    List<ModeleDoc> findAllBySearch(String s);
}
