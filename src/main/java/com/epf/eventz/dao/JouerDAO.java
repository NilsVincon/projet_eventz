package com.epf.eventz.dao;

import com.epf.eventz.model.Jouer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JouerDAO extends JpaRepository<Jouer, Long> {
}
