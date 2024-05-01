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
public class Suivre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_suivre;

    @ManyToOne
    @JoinColumn(name = "suiveur_id")
    private Utilisateur suiveur;

    @ManyToOne
    @JoinColumn(name = "suivi_id")
    private Utilisateur suivi;

}
