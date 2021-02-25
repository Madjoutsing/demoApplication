package com.example.demo.modele;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
@Table(name = "fichenotation")
@AllArgsConstructor
@NoArgsConstructor
public class FicheNotation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_fiche")
    private Long idFiche;
    @Column(name = "nom_entreprise")
    private String nomEntreprise;
    @Column(name = "nom_etudiant")
    private String nomEtudiant;
    @Column(name = "path_file")
    private String pathFile;


    @ManyToOne
    private MembrePedagogie membrepedagogie;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FicheNotation that = (FicheNotation) o;
        return Objects.equals(idFiche, that.idFiche) &&
                Objects.equals(nomEntreprise, that.nomEntreprise) &&
                Objects.equals(nomEtudiant, that.nomEtudiant) &&
                Objects.equals(pathFile, that.pathFile) &&
                Objects.equals(membrepedagogie, that.membrepedagogie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFiche, nomEntreprise, nomEtudiant, pathFile, membrepedagogie);
    }

    @Override
    public String toString() {
        return "FicheNotation{" +
                "idFiche=" + idFiche +
                ", nomEntreprise='" + nomEntreprise + '\'' +
                ", nomEtudiant='" + nomEtudiant + '\'' +
                ", pathFile='" + pathFile + '\'' +
                ", membrepedagogie=" + membrepedagogie +
                '}';
    }
}
