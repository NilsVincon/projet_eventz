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
public class PrefererTypeMusique {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_preferer_type_musique;

    @ManyToOne
    @JoinColumn(name = "typeMusique")
    private TypeMusique typeMusique;

    @ManyToOne
    @JoinColumn(name = "utilisateur")
    private Utilisateur utilisateur;

}
