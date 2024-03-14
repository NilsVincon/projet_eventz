package com.epf.eventz.model;

public class AvoirTypeMusique {
    private int avoir_type_musique;
    private int id_type_musique;
    private int id_evenement;

    public AvoirTypeMusique() {}

    public AvoirTypeMusique(int avoir_type_musique, int id_type_musique, int id_evenement) {
        this.avoir_type_musique = avoir_type_musique;
        this.id_type_musique = id_type_musique;
        this.id_evenement = id_evenement;
    }

    public int getAvoir_type_musique() {
        return avoir_type_musique;
    }

    public void setAvoir_type_musique(int avoir_type_musique) {
        this.avoir_type_musique = avoir_type_musique;
    }

    public int getId_type_musique() {
        return id_type_musique;
    }

    public void setId_type_musique(int id_type_musique) {
        this.id_type_musique = id_type_musique;
    }

    public int getId_evenement() {
        return id_evenement;
    }

    public void setId_evenement(int id_evenement) {
        this.id_evenement = id_evenement;
    }
}
