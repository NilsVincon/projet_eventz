package com.epf.eventz.dao;

import com.epf.eventz.exception.DAOException;
import com.epf.eventz.model.Suivre;
import com.epf.eventz.model.Utilisateur;
import com.epf.eventz.persistence.ConnectionManager;
import org.springframework.data.jpa.repository.JpaRepository;
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


}
