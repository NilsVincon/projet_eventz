package com.epf.eventz.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Participe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_participe;

    @ManyToOne
    @JoinColumn(name="idEvenement")
    private Evenement evenement;

    @ManyToOne
    @JoinColumn(name = "id_utilisateur")
    private Utilisateur utilisateur;

    public Participe(Evenement evenement, Utilisateur utilisateur) {
        this.evenement = evenement;
        this.utilisateur = utilisateur;
    }
}
