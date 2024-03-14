package com.epf.eventz.model;

import java.time.LocalDate;

public class Evenement {
    private int id_evenement;
    private int id_statut_evenement;
    private int id_type_evenement;
    private String nom_evenement;
    private String description_evenement;
    private LocalDate debut_evenement;
    private LocalDate fin_evenement;
    private String adresse_evenement;
    private int prix_evenement;
    private int nb_place_evenement;

    public Evenement() {}

    public Evenement(int id_evenement, int id_statut_evenement, int id_type_evenement, String nom_evenement, String description_evenement, LocalDate debut_evenement, LocalDate fin_evenement, String adresse_evenement, int prix_evenement, int nb_place_evenement) {
        this.id_evenement = id_evenement;
        this.id_statut_evenement = id_statut_evenement;
        this.id_type_evenement = id_type_evenement;
        this.nom_evenement = nom_evenement;
        this.description_evenement = description_evenement;
        this.debut_evenement = debut_evenement;
        this.fin_evenement = fin_evenement;
        this.adresse_evenement = adresse_evenement;
        this.prix_evenement = prix_evenement;
        this.nb_place_evenement = nb_place_evenement;
    }

    public int getId_evenement() {
        return id_evenement;
    }

    public void setId_evenement(int id_evenement) {
        this.id_evenement = id_evenement;
    }

    public int getId_statut_evenement() {
        return id_statut_evenement;
    }

    public void setId_statut_evenement(int id_statut_evenement) {
        this.id_statut_evenement = id_statut_evenement;
    }

    public int getId_type_evenement() {
        return id_type_evenement;
    }

    public void setId_type_evenement(int id_type_evenement) {
        this.id_type_evenement = id_type_evenement;
    }

    public String getNom_evenement() {
        return nom_evenement;
    }

    public void setNom_evenement(String nom_evenement) {
        this.nom_evenement = nom_evenement;
    }

    public String getDescription_evenement() {
        return description_evenement;
    }

    public void setDescription_evenement(String description_evenement) {
        this.description_evenement = description_evenement;
    }

    public LocalDate getDebut_evenement() {
        return debut_evenement;
    }

    public void setDebut_evenement(LocalDate debut_evenement) {
        this.debut_evenement = debut_evenement;
    }

    public LocalDate getFin_evenement() {
        return fin_evenement;
    }

    public void setFin_evenement(LocalDate fin_evenement) {
        this.fin_evenement = fin_evenement;
    }

    public String getAdresse_evenement() {
        return adresse_evenement;
    }

    public void setAdresse_evenement(String adresse_evenement) {
        this.adresse_evenement = adresse_evenement;
    }

    public int getPrix_evenement() {
        return prix_evenement;
    }

    public void setPrix_evenement(int prix_evenement) {
        this.prix_evenement = prix_evenement;
    }

    public int getNb_place_evenement() {
        return nb_place_evenement;
    }

    public void setNb_place_evenement(int nb_place_evenement) {
        this.nb_place_evenement = nb_place_evenement;
    }
}
