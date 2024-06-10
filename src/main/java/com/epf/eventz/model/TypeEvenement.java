package com.epf.eventz.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

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
    private TypeEvenementEnum description_type_evenement;

    @OneToMany(mappedBy = "typeEvenement")
    private List<Evenement> evenements;

    @OneToMany(mappedBy = "typeEvenement")
    private List<PrefererTypeEvenement> prefererTypeEvenements;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeEvenement that = (TypeEvenement) o;
        return Objects.equals(description_type_evenement,that.description_type_evenement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description_type_evenement);
    }

    @Override
    public String toString() {
        return description_type_evenement.toString();
    }
}
