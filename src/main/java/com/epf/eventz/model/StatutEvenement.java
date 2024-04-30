package com.epf.eventz.model;

import jakarta.persistence.*;

@Entity
@Table
public class StatutEvenement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id_statut_evenement;
    public String description_statut_evenement;

    public StatutEvenement() {}
    public StatutEvenement(Long statut_evenement, String description_statut_evenement) {
        this.id_statut_evenement = statut_evenement;
        this.description_statut_evenement = description_statut_evenement;
    }

    public Long getId_statut_evenement() {
        return id_statut_evenement;
    }

    public void setId_statut_evenement(Long id_statut_evenement) {
        this.id_statut_evenement = id_statut_evenement;
    }

    public String getDescription_statut_evenement() {
        return description_statut_evenement;
    }

    public void setDescription_statut_evenement(String description_statut_evenement) {
        this.description_statut_evenement = description_statut_evenement;
    }
}