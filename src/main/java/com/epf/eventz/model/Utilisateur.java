package com.epf.eventz.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_utilisateur;

    private String nom_utilisateur;

    private String prenom_utilisateur;

    private String email_utilisateur;

    private String mdp_utilisateur;

    private String username;

    private String sexe_utilisateur;

    private Boolean admin_utilisateur;

    private LocalDate naissance_utilisateur;

    private String description_utilisateur;

    @OneToMany(mappedBy = "suiveur")
    private List<Suivre> suivis;

    @OneToMany(mappedBy = "suivi")
    private List<Suivre> suiveurs;

    @OneToMany(mappedBy = "utilisateur")
    private List<PrefererArtiste> prefererArtistes;

    @OneToMany(mappedBy = "utilisateur")
    private List<PrefererTypeMusique> prefererTypeMusiques;

    @OneToMany(mappedBy = "utilisateur")
    private List<Participe> participes;

    @OneToMany(mappedBy = "utilisateur")
    private List<PrefererTypeEvenement> prefererTypeEvenements;
}
