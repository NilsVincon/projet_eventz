package com.epf.eventz.dao;

import com.epf.eventz.exception.DAOException;
import com.epf.eventz.model.Artiste;
import com.epf.eventz.model.PrefererArtiste;
import com.epf.eventz.model.Utilisateur;
import com.epf.eventz.persistence.ConnectionManager;
import com.epf.eventz.service.ArtisteService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface PrefererArtisteDAO extends JpaRepository<PrefererArtiste, Long> {
    boolean existsByArtisteAndUtilisateur(Artiste artiste, Utilisateur utilisateur);
    Optional<PrefererArtiste> findByArtisteAndUtilisateur(Artiste artiste, Utilisateur utilisateur);


}
