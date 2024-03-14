package com.epf.eventz.model;

import java.time.LocalDate;

public class Utilisateur {
    private int id_utilisateur;
    private String nom_utilisateur;
    private String prenom_utilisateur;
    private String email_utilisateur;
    private String mdp_utilisateur;
    private String pseudo_utilisateur;
    private String sexe_utilisateur;
    private Boolean admin_utilisateur;
    private LocalDate naissance_utilisateur;
    private String description_utilisateur;

    public Utilisateur(){}
    public Utilisateur(int id_utilisateur, String nom_utilisateur, String prenom_utilisateur, String email_utilisateur, String mdp_utilisateur, String pseudo_utilisateur, String sexe_utilisateur, Boolean admin_utilisateur, LocalDate naissance_utilisateur, String description_utilisateur) {
        this.id_utilisateur = id_utilisateur;
        this.nom_utilisateur = nom_utilisateur;
        this.prenom_utilisateur = prenom_utilisateur;
        this.email_utilisateur = email_utilisateur;
        this.mdp_utilisateur = mdp_utilisateur;
        this.pseudo_utilisateur = pseudo_utilisateur;
        this.sexe_utilisateur = sexe_utilisateur;
        this.admin_utilisateur = admin_utilisateur;
        this.naissance_utilisateur = naissance_utilisateur;
        this.description_utilisateur = description_utilisateur;
    }

    public Utilisateur(int id_utilisateur, String email_utilisateur, String mdp_utilisateur, String pseudo_utilisateur, Boolean admin_utilisateur, LocalDate naissance_utilisateur, String description_utilisateur) {
        this.id_utilisateur = id_utilisateur;
        this.email_utilisateur = email_utilisateur;
        this.mdp_utilisateur = mdp_utilisateur;
        this.pseudo_utilisateur = pseudo_utilisateur;
        this.admin_utilisateur = admin_utilisateur;
        this.naissance_utilisateur = naissance_utilisateur;
        this.description_utilisateur = description_utilisateur;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public String getNom_utilisateur() {
        return nom_utilisateur;
    }

    public void setNom_utilisateur(String nom_utilisateur) {
        this.nom_utilisateur = nom_utilisateur;
    }

    public String getPrenom_utilisateur() {
        return prenom_utilisateur;
    }

    public void setPrenom_utilisateur(String prenom_utilisateur) {
        this.prenom_utilisateur = prenom_utilisateur;
    }

    public String getEmail_utilisateur() {
        return email_utilisateur;
    }

    public void setEmail_utilisateur(String email_utilisateur) {
        this.email_utilisateur = email_utilisateur;
    }

    public String getMdp_utilisateur() {
        return mdp_utilisateur;
    }

    public void setMdp_utilisateur(String mdp_utilisateur) {
        this.mdp_utilisateur = mdp_utilisateur;
    }

    public String getPseudo_utilisateur() {
        return pseudo_utilisateur;
    }

    public void setPseudo_utilisateur(String pseudo_utilisateur) {
        this.pseudo_utilisateur = pseudo_utilisateur;
    }

    public String getSexe_utilisateur() {
        return sexe_utilisateur;
    }

    public void setSexe_utilisateur(String sexe_utilisateur) {
        this.sexe_utilisateur = sexe_utilisateur;
    }

    public Boolean getAdmin_utilisateur() {
        return admin_utilisateur;
    }

    public void setAdmin_utilisateur(Boolean admin_utilisateur) {
        this.admin_utilisateur = admin_utilisateur;
    }

    public LocalDate getNaissance_utilisateur() {
        return naissance_utilisateur;
    }

    public void setNaissance_utilisateur(LocalDate naissance_utilisateur) {
        this.naissance_utilisateur = naissance_utilisateur;
    }

    public String getDescription_utilisateur() {
        return description_utilisateur;
    }

    public void setDescription_utilisateur(String description_utilisateur) {
        this.description_utilisateur = description_utilisateur;
    }
}
