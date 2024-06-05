package com.epf.eventz.dao;

import com.epf.eventz.exception.DAOException;
import com.epf.eventz.model.Evenement;
import com.epf.eventz.model.Participe;
import com.epf.eventz.model.Utilisateur;
import com.epf.eventz.persistence.ConnectionManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ParticipeDAO extends JpaRepository<Participe, Long> {

    boolean existsByUtilisateurAndEvenement(Utilisateur utilisateur, Evenement evenement);

    @Query("SELECT COUNT(p) FROM Participe p WHERE p.evenement = :evenement")
    int countParticipantsByEvenement(@Param("evenement") Evenement evenement);

    @Query("SELECT p.evenement FROM Participe p WHERE p.utilisateur = :utilisateur")
    List<Evenement> findEvenementsByUtilisateur(@Param("utilisateur") Utilisateur utilisateur);

    @Query("SELECT p.utilisateur FROM Participe p WHERE p.evenement = :evenement")
    List<Utilisateur> findUtilisateurByEvent(@Param("evenement") Evenement evenement);

    @Query("SELECT p.utilisateur FROM Participe p WHERE p.evenement = :evenement AND p.utilisateur IN :amis")
    List<Utilisateur> findParticipantsByEvenementAndAmis(@Param("evenement") Evenement evenement, @Param("amis") List<Utilisateur> amis);

}
