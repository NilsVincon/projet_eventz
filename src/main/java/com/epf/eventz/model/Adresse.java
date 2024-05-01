package com.epf.eventz.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Adresse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_adresse;

    private String numero_adresse;

    private String rue_adresse;

    private String ville_adresse;

    private int code_postal_adresse;

    private String pays_adresse;

    private boolean public_adresse;

    @OneToMany(mappedBy = "adresse")
    private List<Evenement> evenements;

}