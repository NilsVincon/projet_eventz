package com.epf.eventz.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Jouer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_jouer;

    @ManyToOne
    @JoinColumn(name = "id_artiste")
    private Artiste artiste;

    @ManyToOne
    @JoinColumn(name = "id_typeMusique")
    private TypeMusique typeMusique;

    public Jouer(Artiste artiste, TypeMusique typeMusique) {
        this.artiste = artiste;
        this.typeMusique = typeMusique;
    }
}
