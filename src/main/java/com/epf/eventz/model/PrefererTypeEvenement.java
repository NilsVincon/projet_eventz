package com.epf.eventz.model;

public class PrefererTypeEvenement {
    private int id_preferer_type_evenement;
    private int id_type_evenement;
    private int id_utilisateur;

    public PrefererTypeEvenement() {}

    public PrefererTypeEvenement(int id_preferer_type_evenement, int id_type_evenement, int id_utilisateur) {
        this.id_preferer_type_evenement = id_preferer_type_evenement;
        this.id_type_evenement = id_type_evenement;
        this.id_utilisateur = id_utilisateur;
    }

    public int getId_preferer_type_evenement() {
        return id_preferer_type_evenement;
    }

    public void setId_preferer_type_evenement(int id_preferer_type_evenement) {
        this.id_preferer_type_evenement = id_preferer_type_evenement;
    }

    public int getId_type_evenement() {
        return id_type_evenement;
    }

    public void setId_type_evenement(int id_type_evenement) {
        this.id_type_evenement = id_type_evenement;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }
}
