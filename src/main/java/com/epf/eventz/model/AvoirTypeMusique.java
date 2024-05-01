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
public class AvoirTypeMusique {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_avoirTypeMusique;

    @ManyToOne
    @JoinColumn(name = "typeMusique")
    private TypeMusique typeMusique;

    @ManyToOne
    @JoinColumn(name = "evenement")
    private Evenement evenement;

}
