package com.example.demo.modele;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table (name = "typedoc")
public class TypeDoc {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_type")
    protected Long idType;
    @Column(name = "nom_type_doc")
    protected String nomTypeDoc;

    public TypeDoc() {
    }

    public TypeDoc(String nomTypeDoc) {
        this.nomTypeDoc = nomTypeDoc;
    }
}
