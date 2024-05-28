package com.epf.eventz.dao;

import com.epf.eventz.exception.DAOException;
import com.epf.eventz.model.Evenement;
import com.epf.eventz.model.Participe;
import com.epf.eventz.model.Utilisateur;
import com.epf.eventz.persistence.ConnectionManager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ParticipeDAO extends JpaRepository<Participe, Long> {

    boolean existsByUtilisateurAndEvenement(Utilisateur utilisateur, Evenement evenement);

}
