package com.example.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CycleDTO {

    protected Long idCycle;
    protected String nomCycle;

    protected List<FiliereDTO> filiereDTOS = new ArrayList<>();
}
