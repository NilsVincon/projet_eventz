package com.epf.eventz.model;

public class Suivre {
    private int id_suivre;
    private int id_utilisateur;
    private int id_utilisateur_suivie;

    public Suivre() {}

    public Suivre(int id_suivre, int id_utilisateur, int id_utilisateur_suivie) {
        this.id_suivre = id_suivre;
        this.id_utilisateur = id_utilisateur;
        this.id_utilisateur_suivie = id_utilisateur_suivie;
    }

    public int getId_suivre() {
        return id_suivre;
    }

    public void setId_suivre(int id_suivre) {
        this.id_suivre = id_suivre;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public int getId_utilisateur_suivie() {
        return id_utilisateur_suivie;
    }

    public void setId_utilisateur_suivie(int id_utilisateur_suivie) {
        this.id_utilisateur_suivie = id_utilisateur_suivie;
    }
}
