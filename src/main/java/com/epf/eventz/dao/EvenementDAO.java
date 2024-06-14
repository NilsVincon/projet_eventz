package com.epf.eventz.dao;

import com.epf.eventz.model.Evenement;

import com.epf.eventz.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EvenementDAO extends JpaRepository<Evenement, Long> {
    boolean existsByOrganisateurAndIdEvenement(Utilisateur organisateur, Long id);

    List<Evenement> findByOrganisateur(Utilisateur organisateur);

    @Query("SELECT COUNT(e) FROM Evenement e WHERE e.organisateur = :organisateur")
    Long countByOrganisateur(@Param("organisateur") Utilisateur organisateur);
}

