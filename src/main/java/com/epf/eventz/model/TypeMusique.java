package com.epf.eventz.model;

import com.epf.eventz.dao.AvoirTypeMusiqueDAO;
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
public class TypeMusique {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_type_musique;

    private String description_type_musique;

    @OneToMany(mappedBy = "typeMusique")
    private List<PrefererTypeMusique> prefererTypeMusiques;

    @OneToMany(mappedBy = "typeMusique")
    private List<AvoirTypeMusique> avoirTypeMusiques;

    @OneToMany(mappedBy = "typeMusique")
    private List<Jouer> jouers;

}
