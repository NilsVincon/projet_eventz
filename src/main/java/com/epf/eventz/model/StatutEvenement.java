package com.epf.eventz.model;

public class StatutEvenement {
    public int statut_evenement;
    public String description_statut_evenement;

    public StatutEvenement() {}
    public StatutEvenement(int statut_evenement, String description_statut_evenement) {
        this.statut_evenement = statut_evenement;
        this.description_statut_evenement = description_statut_evenement;
    }

    public int getStatut_evenement() {
        return statut_evenement;
    }

    public void setStatut_evenement(int statut_evenement) {
        this.statut_evenement = statut_evenement;
    }

    public String getDescription_statut_evenement() {
        return description_statut_evenement;
    }

    public void setDescription_statut_evenement(String description_statut_evenement) {
        this.description_statut_evenement = description_statut_evenement;
    }
}
