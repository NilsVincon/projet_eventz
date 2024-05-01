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
public class PrefererArtiste {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_preferer_artiste;

    @ManyToOne
    @JoinColumn(name ="artiste")
    private Artiste artiste;

    @ManyToOne
    @JoinColumn(name = "utilisateur")
    private Utilisateur utilisateur;

}
