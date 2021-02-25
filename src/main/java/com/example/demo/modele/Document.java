package com.example.demo.modele;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;


@Entity
@Data
@Table (name = "document")
@AllArgsConstructor
@NoArgsConstructor
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_doc")
    private Long idDoc;
    @Column(name = "date")
    private LocalDateTime date;
    @Column(name = "titre")
    private String titre;
    @Column(name = "theme")
    private String theme;
    @Column(name = "etat")
    private String etat;
    @Column(name = "version")
    private String version;
    @Column(name = "taille")
    private long taille;
    @Column(name = "nom_entreprise")
    private String nomEntreprise;
    @Column(name = "mot_cle")
    private String motCle;
    @Column(name = "date_scolarite")
    private int dateScolarite;
    @Column(name = "date_soutenance")
    private int dateSoutenance;
    @Column(name = "path_file")
    private String pathFile;



    @JsonIgnore
    @ManyToOne
    private Etudiant etudiant;

    @JsonIgnore
    @ManyToOne
    private OptionFiliere optionFiliere;

//    @ManyToOne()
//    @JoinColumn(name = "id_type")
//    private TypeDoc typeDoc;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Document document = (Document) o;
        return taille == document.taille &&
                Objects.equals(idDoc, document.idDoc) &&
                Objects.equals(date, document.date) &&
                Objects.equals(titre, document.titre) &&
                Objects.equals(theme, document.theme) &&
                Objects.equals(etat, document.etat) &&
                Objects.equals(version, document.version) &&
                Objects.equals(nomEntreprise, document.nomEntreprise) &&
                Objects.equals(motCle, document.motCle) &&
                Objects.equals(dateScolarite, document.dateScolarite) &&
                Objects.equals(dateSoutenance, document.dateSoutenance) &&
                Objects.equals(pathFile, document.pathFile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDoc, date, titre, theme, etat, version, taille, nomEntreprise, motCle, dateScolarite, dateSoutenance, pathFile);
    }

    @Override
    public String toString() {
        return "Document{" +
                "idDoc='" + idDoc + '\'' +
                ", date=" + date +
                ", titre='" + titre + '\'' +
                ", theme='" + theme + '\'' +
                ", etat='" + etat + '\'' +
                ", version='" + version + '\'' +
                ", taille=" + taille +
                ", nomEntreprise='" + nomEntreprise + '\'' +
                ", motCle='" + motCle + '\'' +
                ", dateScolarite=" + dateScolarite +
                ", dateSoutenance=" + dateSoutenance +
                ", pathFile='" + pathFile + '\'' +
                '}';
    }
}
