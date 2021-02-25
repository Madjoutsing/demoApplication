package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentDTO {
    private String idDoc;
    private LocalDateTime date;
    private String titre;
    private String theme;
    private String etat;
    private String version;
    private long taille;
    private String nomEntreprise;
    private String motCle;
    private int dateScolarite;
    private int dateSoutenance;
    private String pathFile;

    private OptionFiliereDTO optionDTO;
    private EtudiantDTO etudiantDTO;


}

