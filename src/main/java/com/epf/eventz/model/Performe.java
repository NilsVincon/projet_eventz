package com.epf.eventz.model;


import java.util.Date;

public class Performe {
    private int id_evenement;
    private int id_artiste;
    private Date date_debut_performe;
    private Date date_fin_performe;

    public Performe() {}

    public Performe( int id_evenement, int id_artiste, Date date_debut_performe, Date date_fin_performe) {
        this.id_evenement = id_evenement;
        this.id_artiste = id_artiste;
        this.date_debut_performe = date_debut_performe;
        this.date_fin_performe = date_fin_performe;
    }


    public int getId_evenement() {
        return id_evenement;
    }

    public void setId_evenement(int id_evenement) {
        this.id_evenement = id_evenement;
    }

    public int getId_artiste() {
        return id_artiste;
    }

    public void setId_artiste(int id_artiste) {
        this.id_artiste = id_artiste;
    }

    public Date getDate_debut_performe() {
        return date_debut_performe;
    }

    public void setDate_debut_performe(Date date_debut_performe) {
        this.date_debut_performe = date_debut_performe;
    }

    public Date getDate_fin_performe() {
        return date_fin_performe;
    }

    public void setDate_fin_performe(Date date_fin_performe) {
        this.date_fin_performe = date_fin_performe;
    }
}
