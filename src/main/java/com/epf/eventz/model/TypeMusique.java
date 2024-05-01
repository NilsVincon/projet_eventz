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
public class TypeMusique {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_type_musique;

    private String description_type_musique;

    @OneToMany(mappedBy = "typeMusique")
    private List<PrefererTypeMusique> prefererTypeMusiques;

}
