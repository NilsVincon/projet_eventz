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

    private String password;

    private String username;

    private String sexe_utilisateur;

    private String role_utilisateur;

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

    public Utilisateur(String nom_utilisateur, String prenom_utilisateur, String email_utilisateur, String password,
                       String username, String sexe_utilisateur, String role_utilisateur, LocalDate naissance_utilisateur,
                       String description_utilisateur) {
        this.nom_utilisateur = nom_utilisateur;
        this.prenom_utilisateur = prenom_utilisateur;
        this.email_utilisateur = email_utilisateur;
        this.password = password;
        this.username = username;
        this.sexe_utilisateur = sexe_utilisateur;
        this.role_utilisateur = role_utilisateur;
        this.naissance_utilisateur = naissance_utilisateur;
        this.description_utilisateur = description_utilisateur;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "id_utilisateur=" + id_utilisateur +
                ", nom_utilisateur='" + nom_utilisateur + '\'' +
                ", prenom_utilisateur='" + prenom_utilisateur + '\'' +
                ", email_utilisateur='" + email_utilisateur + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", sexe_utilisateur='" + sexe_utilisateur + '\'' +
                ", role_utilisateur='" + role_utilisateur + '\'' +
                ", naissance_utilisateur=" + naissance_utilisateur +
                ", description_utilisateur='" + description_utilisateur + '\'' +
                ", suivis=" + suivis +
                ", suiveurs=" + suiveurs +
                ", prefererArtistes=" + prefererArtistes +
                ", prefererTypeMusiques=" + prefererTypeMusiques +
                ", participes=" + participes +
                ", prefererTypeEvenements=" + prefererTypeEvenements +
                '}';
    }
}
