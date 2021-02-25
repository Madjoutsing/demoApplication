package com.example.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EtudiantDTO extends PersonneDTO {

    protected String niveau;

    protected CycleDTO cycleDTO;
    protected FiliereDTO filiereDTO;
    protected OptionFiliereDTO optionFiliereDTO;


}
