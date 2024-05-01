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
public class PrefererTypeEvenement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_preferer_type_evenement;

    @ManyToOne
    @JoinColumn
    private TypeEvenement typeEvenement;

    @ManyToOne
    @JoinColumn
    private Utilisateur utilisateur;

}
