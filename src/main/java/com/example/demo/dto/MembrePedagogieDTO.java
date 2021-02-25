package com.example.demo.dto;


import com.example.demo.modele.MembrePedagogie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MembrePedagogieDTO extends PersonneDTO {
    private List<MembrePedagogie> membrepedagogies=new ArrayList<>();

    protected String fonction;
}
