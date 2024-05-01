package com.epf.eventz.dao;

import com.epf.eventz.exception.DAOException;
import com.epf.eventz.model.Performe;
import com.epf.eventz.persistence.ConnectionManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.LongFunction;

@Repository
public interface PerformeDAO extends JpaRepository<Performe, Long> {
}
