package com.example.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FicheNotationDTO {
    private String idFiche;
    private String nomEntreprise;
    private String nomEtudiant;
    private String pathFile;


}
