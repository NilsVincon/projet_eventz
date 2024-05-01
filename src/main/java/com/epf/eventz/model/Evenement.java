package com.epf.eventz.model;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Evenement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_evenement;

    @ManyToOne
    @JoinColumn(name = "id_statut_evenement")
    private StatutEvenement statutEvenement;

    @ManyToOne
    @JoinColumn(name= "id_adresse")
    private Adresse adresse;

    @ManyToOne
    @JoinColumn(name = "id_type_evenement")
    private TypeEvenement typeEvenement;

    private String nom_evenement;
    private String description_evenement;
    private LocalDate debut_evenement;
    private LocalDate fin_evenement;
    private float prix_evenement;
    private int nb_place_evenement;

}
