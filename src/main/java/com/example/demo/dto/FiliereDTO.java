package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FiliereDTO {

    protected Long idFiliere;
    protected String nomFiliere;

    protected CycleDTO cycleDTO;
    protected List<FiliereDTO> filiereDTOS;
}
