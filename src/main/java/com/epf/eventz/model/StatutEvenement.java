package com.epf.eventz.model;

public class StatutEvenement {
    public int id_statut_evenement;
    public String description_statut_evenement;

    public StatutEvenement() {}
    public StatutEvenement(int statut_evenement, String description_statut_evenement) {
        this.id_statut_evenement = statut_evenement;
        this.description_statut_evenement = description_statut_evenement;
    }

    public int getId_statut_evenement() {
        return id_statut_evenement;
    }

    public void setId_statut_evenement(int id_statut_evenement) {
        this.id_statut_evenement = id_statut_evenement;
    }

    public String getDescription_statut_evenement() {
        return description_statut_evenement;
    }

    public void setDescription_statut_evenement(String description_statut_evenement) {
        this.description_statut_evenement = description_statut_evenement;
    }
}