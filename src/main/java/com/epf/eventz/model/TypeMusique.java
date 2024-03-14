package com.epf.eventz.model;

public class TypeMusique {
    private int id_type_musique;
    private String description_type_musique;

    public TypeMusique() {}

    public TypeMusique(int id_type_musique, String description_type_musique) {
        this.id_type_musique = id_type_musique;
        this.description_type_musique = description_type_musique;
    }

    public int getId_type_musique() {
        return id_type_musique;
    }

    public void setId_type_musique(int id_type_musique) {
        this.id_type_musique = id_type_musique;
    }

    public String getDescription_type_musique() {
        return description_type_musique;
    }

    public void setDescription_type_musique(String description_type_musique) {
        this.description_type_musique = description_type_musique;
    }
}
