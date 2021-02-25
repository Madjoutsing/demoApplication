package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class PersonneDTO {

    protected String id;
    protected String nom;
    protected String prenom;
    protected String mail;
    protected String adresse;
    protected long numero;
}
