package com.epf.eventz.dao;

import com.epf.eventz.exception.DAOException;
import com.epf.eventz.model.Suivre;
import com.epf.eventz.model.Utilisateur;
import com.epf.eventz.persistence.ConnectionManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface SuivreDAO extends JpaRepository<Suivre, Long> {

    boolean existsBySuiveurAndSuivi(Utilisateur suiveur, Utilisateur suivi);

    default boolean areFriend(Utilisateur user1, Utilisateur user2) {
        return existsBySuiveurAndSuivi(user1, user2) && existsBySuiveurAndSuivi(user2, user1);
    }
    Suivre findBySuiveurAndSuivi(Utilisateur suiveur, Utilisateur suivi);
    void deleteBySuiveurAndSuivi(Utilisateur suiveur, Utilisateur suivi);


    @Query("SELECT s1.suiveur FROM Suivre s1 WHERE s1.suivi = :utilisateur AND EXISTS " +
            "(SELECT s2 FROM Suivre s2 WHERE s2.suiveur = s1.suivi AND s2.suivi = s1.suiveur)")
    List<Utilisateur> findAmisByUtilisateur(@Param("utilisateur") Utilisateur utilisateur);



}
