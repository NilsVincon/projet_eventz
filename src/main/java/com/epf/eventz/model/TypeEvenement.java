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
public class TypeEvenement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_type_evenement;
    private String description_type_evenement;

    @OneToMany(mappedBy = "typeEvenement")
    private List<Evenement> evenements;

    @OneToMany(mappedBy = "typeEvenement")
    private List<PrefererTypeEvenement> prefererTypeEvenements;
}
