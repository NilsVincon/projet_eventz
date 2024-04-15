package com.epf.eventz.dao;

import com.epf.eventz.model.Adresse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
public interface AdresseDAO extends JpaRepository<Adresse, Long> {

}

