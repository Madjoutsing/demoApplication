package com.example.demo.modele;


import com.example.demo.modele.role.AppUser;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Getter
@Setter
@Table(name = "etudiant")
public class Etudiant extends Personne{

    @Column(name = "niveau")
    protected String niveau;

    public Etudiant() {
    }

    public Etudiant(String nom, String prenom, String mail, String adresse, long numero, String niveau) {
        super(nom, prenom, mail, adresse, numero);
        this.niveau=niveau;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_cycle")
    private Cycle cycle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_filiere")
    private Filiere filiere;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_option")
    private OptionFiliere optionFiliere;

    @OneToOne(mappedBy = "etudiant")
    private AppUser appUser;

    @OneToMany(mappedBy = "etudiant", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Document> documents = new ArrayList<>();

    public Etudiant(Etudiant etudiant) {
    }
}
