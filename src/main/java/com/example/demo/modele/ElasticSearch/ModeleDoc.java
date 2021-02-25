package com.example.demo.modele.ElasticSearch;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "modeledoc")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ModeleDoc {
    @Id
    private Long idDoc;
    private String search;
    private String pathFile;
    private int dateScolarite;
}
