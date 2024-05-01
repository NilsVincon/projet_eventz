package com.epf.eventz.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class StatutEvenement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_statut_evenement;

    private String description_statut_evenement;

    @OneToMany(mappedBy = "statutEvenement")
    private List<Evenement> evenements;
}