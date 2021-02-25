package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OptionFiliereDTO {

    protected Long idOption;
    protected String nomOption;

    protected FiliereDTO filiereDTO;
    protected List<DocumentDTO> documentDTOS = new ArrayList<>();
}
