package com.example.demo.modele;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Objects;
@Data
@AllArgsConstructor
@MappedSuperclass
public abstract class Personne {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    protected Long id;
    @Column(name = "nom")
    protected String nom;
    @Column(name = "prenom")
    protected String prenom;
    @Column(name = "mail")
    protected String mail;
    @Column(name = "adresse")
    protected String adresse;
    @Column(name = "numero")
    protected long numero;

    public Personne() {
    }

    public Personne(String nom, String prenom, String mail, String adresse, long numero) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.adresse = adresse;
        this.numero = numero;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Personne personne = (Personne) o;
        return numero == personne.numero &&
                Objects.equals(id, personne.id) &&
                Objects.equals(nom, personne.nom) &&
                Objects.equals(prenom, personne.prenom) &&
                Objects.equals(mail, personne.mail) &&
                Objects.equals(adresse, personne.adresse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom, prenom, mail, adresse, numero);
    }

    @Override
    public String toString() {
        return "Personne{" +
                "id='" + id + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", mail='" + mail + '\'' +
                ", adresse='" + adresse + '\'' +
                ", numero=" + numero +
                '}';
    }
}


