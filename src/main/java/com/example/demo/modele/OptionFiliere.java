package com.example.demo.modele;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table (name = "option_filiere")
public class OptionFiliere {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_option")
    protected Long idOption;
    @Column(name = "nom_option")
    protected String nomOption;

    public OptionFiliere() {
    }

    public OptionFiliere(String nomOption) {
        this.nomOption = nomOption;
    }

    @ManyToOne()
    @JoinColumn(name = "id_cycle")
    private Cycle cycle;

    @OneToMany(targetEntity = Document.class, mappedBy = "optionFiliere", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Document> documents = new ArrayList<>();
}
