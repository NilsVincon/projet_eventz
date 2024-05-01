package com.epf.eventz.dao;

import com.epf.eventz.model.Evenement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EvenementDAO extends JpaRepository<Evenement, Long> {

}

