package com.epf.eventz.model;

public class PrefererTypeMusique {
    private int id_preferer_type_musique;
    private int id_type_musique;
    private int id_utilisateur;

    public PrefererTypeMusique() {}

    public PrefererTypeMusique(int id_preferer_type_musique, int id_type_musique, int id_utilisateur) {
        this.id_preferer_type_musique = id_preferer_type_musique;
        this.id_type_musique = id_type_musique;
        this.id_utilisateur = id_utilisateur;
    }

    public int getId_preferer_type_musique() {
        return id_preferer_type_musique;
    }

    public void setId_preferer_type_musique(int id_preferer_type_musique) {
        this.id_preferer_type_musique = id_preferer_type_musique;
    }

    public int getId_type_musique() {
        return id_type_musique;
    }

    public void setId_type_musique(int id_type_musique) {
        this.id_type_musique = id_type_musique;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }
}
