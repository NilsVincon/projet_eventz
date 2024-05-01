package com.epf.eventz.dao;

import com.epf.eventz.exception.DAOException;
import com.epf.eventz.model.Utilisateur;
import com.epf.eventz.persistence.ConnectionManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository
public interface UtilisateurDAO extends JpaRepository<Utilisateur, Long> {
    Optional<Utilisateur> findByUsername(String username);

}
