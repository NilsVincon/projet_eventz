package com.epf.eventz.model;

import jakarta.persistence.*;

@Entity
@Table
public class Adresse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_adresse;

    private String numero_adresse;

    private String rue_adresse;

    private String ville_adresse;

    private int code_postal_adresse;

    private String pays_adresse;
    private boolean public_adresse;


    public Adresse() {}

    public Adresse(String numero_adresse, String rue_adresse, String ville_adresse, int code_postal_adresse, String pays_adresse, boolean public_adresse) {
        this.numero_adresse = numero_adresse;
        this.rue_adresse = rue_adresse;
        this.ville_adresse = ville_adresse;
        this.code_postal_adresse = code_postal_adresse;
        this.pays_adresse = pays_adresse;
        this.public_adresse = public_adresse;
    }

    public long getId_adresse() {
        return id_adresse;
    }

    public void setId_adresse(long id_adresse) {
        this.id_adresse = id_adresse;
    }

    public String getNumero_adresse() {
        return numero_adresse;
    }

    public void setNumero_adresse(String numero_adresse) {
        this.numero_adresse = numero_adresse;
    }

    public String getRue_adresse() {
        return rue_adresse;
    }

    public void setRue_adresse(String rue_adresse) {
        this.rue_adresse = rue_adresse;
    }

    public String getVille_adresse() {
        return ville_adresse;
    }

    public void setVille_adresse(String ville_adresse) {
        this.ville_adresse = ville_adresse;
    }

    public int getCode_postal_adresse() {
        return code_postal_adresse;
    }

    public void setCode_postal_adresse(int code_postal_adresse) {
        this.code_postal_adresse = code_postal_adresse;
    }

    public String getPays_adresse() {
        return pays_adresse;
    }

    public void setPays_adresse(String pays_adresse) {
        this.pays_adresse = pays_adresse;
    }

    public boolean isPublic_adresse() {
        return public_adresse;
    }

    public void setPublic_adresse(boolean public_adresse) {
        this.public_adresse = public_adresse;
    }
}