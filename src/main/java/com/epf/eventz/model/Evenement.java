package com.epf.eventz.model;

import com.epf.eventz.dao.AvoirTypeMusiqueDAO;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Evenement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEvenement;

    @ManyToOne
    @JoinColumn(name = "id_statut_evenement")
    private StatutEvenement statutEvenement;

    @ManyToOne
    @JoinColumn(name = "id_organisateur")
    private Utilisateur organisateur;

    @ManyToOne
    @JoinColumn(name = "id_adresse")
    private Adresse adresse;

    @ManyToOne
    @JoinColumn(name = "id_type_evenement")
    private TypeEvenement typeEvenement;

    @OneToMany(mappedBy = "evenement")
    private List<AvoirTypeMusique> avoirTypeMusiques;

    @OneToMany(mappedBy = "evenement")
    private List<Performe> performes;

    @OneToMany(mappedBy = "evenement", cascade = CascadeType.ALL)
    private List<Participe> participes;

    private String nom_evenement;
    private String description_evenement;
    private LocalDate debut_evenement;
    private LocalDate fin_evenement;
    private float prix_evenement;
    private int nb_place_evenement;
    private Boolean public_evenement;

    @Column(columnDefinition = "LONGBLOB")
    private byte[] pdpEvenement;

    public Evenement(String nom_evenement, String description_evenement, LocalDate debut_evenement, LocalDate fin_evenement, float prix_evenement, int nb_place_evenement, boolean public_evenement) {
        this.nom_evenement = nom_evenement;
        this.description_evenement = description_evenement;
        this.debut_evenement = debut_evenement;
        this.fin_evenement = fin_evenement;
        this.prix_evenement = prix_evenement;
        this.nb_place_evenement = nb_place_evenement;
        this.public_evenement = public_evenement;
    }

    @Override
    public String toString() {
        return "Evenement{" +
                ", nom_evenement='" + nom_evenement + '\'' +
                ", description_evenement='" + description_evenement + '\'' +
                ", debut_evenement=" + debut_evenement +
                ", fin_evenement=" + fin_evenement +
                ", prix_evenement=" + prix_evenement +
                ", nb_place_evenement=" + nb_place_evenement +
                '}';
    }

    public Boolean hasPdp() {
        return pdpEvenement != null;
    }

    public String printDebut(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.FRENCH);
        return debut_evenement.format(formatter);
    }

    public String printFin(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.FRENCH);
        return fin_evenement.format(formatter);
    }

}

