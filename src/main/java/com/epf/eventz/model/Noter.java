package com.epf.eventz.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Noter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_noter;

    @ManyToOne
    @JoinColumn
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn
    private Evenement evenement;

    @Column(nullable = false)
    private int note;

    @Column(length = 500)
    private String commentaire;

    public void setNote(int note) {
        if (note < 1 || note > 5) {
            throw new IllegalArgumentException("La note doit être comprise entre 1 et 5.");
        }
        this.note = note;
    }
}
