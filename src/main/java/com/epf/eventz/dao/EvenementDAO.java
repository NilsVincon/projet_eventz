package com.epf.eventz.dao;

import com.epf.eventz.model.Evenement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
public interface EvenementDAO extends JpaRepository<Evenement, Long> {

}

