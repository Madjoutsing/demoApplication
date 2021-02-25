package com.example.demo.modele;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "cycle")
public class Cycle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_cycle")
    protected Long idCycle;
    @Column(name = "nom_cycle")
    protected String nomCycle;

    public Cycle() {
    }

    public Cycle(String nomCycle) {
        this.nomCycle = nomCycle;
    }

}
