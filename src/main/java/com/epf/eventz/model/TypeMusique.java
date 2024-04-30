package com.epf.eventz.model;

import jakarta.persistence.*;

@Entity
@Table
public class TypeMusique {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_type_musique;
    private String description_type_musique;

    public TypeMusique() {}

    public TypeMusique(String description_type_musique) {
        this.description_type_musique = description_type_musique;
    }
    public TypeMusique(Long id_type_musique, String description_type_musique) {
        this.id_type_musique = id_type_musique;
        this.description_type_musique = description_type_musique;
    }

    public Long getId_type_musique() {
        return id_type_musique;
    }

    public void setId_type_musique(Long id_type_musique) {
        this.id_type_musique = id_type_musique;
    }

    public String getDescription_type_musique() {
        return description_type_musique;
    }

    public void setDescription_type_musique(String description_type_musique) {
        this.description_type_musique = description_type_musique;
    }
}
