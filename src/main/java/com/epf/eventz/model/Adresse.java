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
    @Override
    public String toString() {
        return "Adresse{" +
                "id_adresse=" + id_adresse +
                ", numero_adresse='" + numero_adresse + '\'' +
                ", rue_adresse='" + rue_adresse + '\'' +
                ", ville_adresse='" + ville_adresse + '\'' +
                ", code_postal_adresse=" + code_postal_adresse +
                ", pays_adresse='" + pays_adresse + '\'' +
                '}';
    }
}