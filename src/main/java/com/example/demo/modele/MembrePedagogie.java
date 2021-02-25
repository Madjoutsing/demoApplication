package com.example.demo.modele;


import com.example.demo.modele.role.AppUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "membrepedagogie")
public class MembrePedagogie extends Personne {

    @Column(name = "fonction")
    protected String fonction;

    @OneToOne(mappedBy = "membrePedagogie")
    private AppUser appUser;
    public MembrePedagogie() {
    }

    @JsonIgnore
    @OneToMany(mappedBy = "membrepedagogie", cascade = CascadeType.ALL)
    private List<FicheNotation> ficheNotations = new ArrayList<>();

    public MembrePedagogie(String nom, String prenom, String mail, String adresse, long numero, String fonction) {
        super(nom, prenom, mail, adresse, numero);
        this.fonction=fonction;
    }
}
