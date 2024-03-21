package com.epf.eventz.model;

public class PrefererArtiste {
    private int id_artiste;
    private int id_utilisateur;

    public PrefererArtiste() {}

    public PrefererArtiste( int id_artiste, int id_utilisateur) {
        this.id_artiste = id_artiste;
        this.id_utilisateur = id_utilisateur;
    }


    public int getId_artiste() {
        return id_artiste;
    }

    public void setId_artiste(int id_artiste) {
        this.id_artiste = id_artiste;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }
}
