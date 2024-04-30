package com.epf.eventz.dao;

import com.epf.eventz.exception.DAOException;
import com.epf.eventz.model.StatutEvenement;
import com.epf.eventz.persistence.ConnectionManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface StatutEvenementDAO extends JpaRepository<StatutEvenement, Long> {
}
