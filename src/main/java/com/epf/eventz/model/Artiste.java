package com.epf.eventz.model;


import jakarta.persistence.*;

@Entity
@Table
public class Artiste {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_artiste;
    private String nom_artiste;

    @Override
    public String toString() {
        return "Artiste{" +
                "id_artiste=" + id_artiste +
                ", nom_artiste='" + nom_artiste + '\'' +
                ", description_artiste='" + description_artiste + '\'' +
                '}';
    }

    private String description_artiste;

    public Artiste(){}

    public Artiste(int id_artiste,String nom_artiste,String description_artiste){
        this.id_artiste=id_artiste;
        this.nom_artiste=nom_artiste;
        this.description_artiste=description_artiste;
    }

    public int getId_artiste() {
        return id_artiste;
    }

    public void setId_artiste(int id_artiste) {
        this.id_artiste = id_artiste;
    }

    public String getNom_artiste() {
        return nom_artiste;
    }

    public void setNom_artiste(String nom_artiste) {
        this.nom_artiste = nom_artiste;
    }

    public String getDescription_artiste() {
        return description_artiste;
    }

    public void setDescription_artiste(String description_artiste) {
        this.description_artiste = description_artiste;
    }
}
