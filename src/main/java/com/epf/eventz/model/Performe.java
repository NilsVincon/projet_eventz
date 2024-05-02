package com.epf.eventz.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Entity
@Table
public class Performe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_performe;

    @ManyToOne
    @JoinColumn(name = "evenement")
    private Evenement evenement;

    @ManyToOne
    @JoinColumn(name = "artiste")
    private Artiste artiste;

    private Date date_debut_performe;

    private Date date_fin_performe;
}
