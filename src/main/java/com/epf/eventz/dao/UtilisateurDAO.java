package com.epf.eventz.dao;

import com.epf.eventz.exception.DAOException;
import com.epf.eventz.model.Utilisateur;
import com.epf.eventz.persistence.ConnectionManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UtilisateurDAO extends CrudRepository<Utilisateur,Long> {

    Optional<Utilisateur> findByUsername(String username);
    Boolean existsByUsername(String username);

}

