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

    @Override
    public String toString() {
        return "Artiste{" +
                "id_artiste=" + id_artiste +
                ", nom_artiste='" + nom_artiste + '\'' +
                ", description_artiste='" + description_artiste + '\'' +
                '}';
    }

    private String description_artiste;

    @Column(columnDefinition = "LONGBLOB")
    private byte[] pdpArtiste;

    @OneToMany(mappedBy = "artiste")
    private List<PrefererArtiste> prefererArtistes;

    @OneToMany(mappedBy = "artiste")
    private List<Performe> performances;

    @OneToMany(mappedBy = "artiste")
    private List<Jouer> jouers;
}
