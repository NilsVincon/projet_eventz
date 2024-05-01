package com.epf.eventz.dao;

import com.epf.eventz.exception.DAOException;
import com.epf.eventz.model.PrefererTypeEvenement;
import com.epf.eventz.persistence.ConnectionManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface PrefererTypeEvenementDAO extends JpaRepository<PrefererTypeEvenement, Long> {
}
