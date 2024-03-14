package com.epf.eventz.model;

public class Participe {
    private int id_participe;
    private int id_evenement;
    private int id_utilisateur;

    public Participe() {}

    public Participe(int id_participe, int id_evenement, int id_utilisateur) {
        this.id_participe = id_participe;
        this.id_evenement = id_evenement;
        this.id_utilisateur = id_utilisateur;
    }

    public int getId_participe() {
        return id_participe;
    }

    public void setId_participe(int id_participe) {
        this.id_participe = id_participe;
    }

    public int getId_evenement() {
        return id_evenement;
    }

    public void setId_evenement(int id_evenement) {
        this.id_evenement = id_evenement;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }
}
