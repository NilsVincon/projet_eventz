package com.epf.eventz.dao;

import com.epf.eventz.model.Evenement;
import com.epf.eventz.model.Noter;
import com.epf.eventz.model.Utilisateur;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoterDAO extends JpaRepository<Noter, Long> {

    List<Noter> findAllByEvenement(Evenement evenement, Sort sort);

    boolean existsByEvenementAndUtilisateur(Evenement evenement, Utilisateur utilisateur);
}
