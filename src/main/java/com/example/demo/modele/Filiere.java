package com.example.demo.modele;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "filiere")
public class Filiere {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_filiere")
    protected Long idFiliere;
    @Column(name = "nom_filiere")
    protected String nomFiliere;

    public Filiere() {
    }

    public Filiere(String nomFiliere) {
        this.nomFiliere = nomFiliere;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_option")
    private OptionFiliere optionFiliere;

}
