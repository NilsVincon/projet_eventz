package com.epf.eventz.model;

import jakarta.persistence.*;

@Entity
@Table
public class TypeEvenement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_type_evenement;
    private String description_type_evenement;

    public TypeEvenement() {}

    public TypeEvenement(Long id_type_evenement, String description) {
        this.id_type_evenement = id_type_evenement;
        this.description_type_evenement = description;
    }

    public Long getId_type_evenement() {
        return id_type_evenement;
    }

    public void setId_type_evenement(Long id_type_evenement) {
        this.id_type_evenement = id_type_evenement;
    }

    public String getDescription_type_evenement() {
        return description_type_evenement;
    }

    public void setDescription_type_evenement(String description_type_evenement) {
        this.description_type_evenement = description_type_evenement;
    }
}
