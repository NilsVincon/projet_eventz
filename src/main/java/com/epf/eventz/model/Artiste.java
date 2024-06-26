package com.epf.eventz.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Artiste {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_artiste;

    private String nom_artiste;

    private String description_artiste;

    @Column(columnDefinition = "LONGBLOB")
    private byte[] pdpArtiste;

    @OneToMany(mappedBy = "artiste")
    private List<PrefererArtiste> prefererArtistes;

    @OneToMany(mappedBy = "artiste")
    private List<Performe> performances;

    @OneToMany(mappedBy = "artiste")
    private List<Jouer> jouers;

    public Artiste(String nom_artiste,String description_artiste){
        this.nom_artiste = nom_artiste;
        this.description_artiste = description_artiste;
    }

    public Artiste(String nom_artiste, String description_artiste, List<PrefererArtiste> prefererArtistes, List<Performe> performances, List<Jouer> jouers) {
        this.nom_artiste = nom_artiste;
        this.description_artiste = description_artiste;
        this.prefererArtistes = prefererArtistes;
        this.performances = performances;
        this.jouers = jouers;
    }

    @Override
    public String toString() {
        return nom_artiste;
    }

}
