package com.epf.eventz.dao;

import com.epf.eventz.exception.DAOException;
import com.epf.eventz.model.StatutEvenement;
import com.epf.eventz.persistence.ConnectionManager;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StatutEvenementDAO {

    private static final String CREATE_STATUT_QUERY = "INSERT INTO StatutEvenement(id_statut_evenement, description_statut_evenement) VALUES (?, ?)";
    private static final String DELETE_STATUT_QUERY = "DELETE FROM StatutEvenement WHERE id_statut_evenement=?";
    private static final String FIND_STATUT_QUERY = "SELECT description_statut_evenement FROM StatutEvenement WHERE id_statut_evenement=?";
    private static final String FIND_ALL_STATUTS_QUERY = "SELECT id_statut_evenement, description_statut_evenement FROM StatutEvenement";
    private static final String MODIFY_STATUT_QUERY = "UPDATE StatutEvenement SET description_statut_evenement=? WHERE id_statut_evenement=?";

    public int creerStatut(StatutEvenement statut) throws DAOException {
        try (Connection connexion = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(CREATE_STATUT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, statut.getId_statut_evenement());
            preparedStatement.setString(2, statut.getDescription_statut_evenement());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e.getCause());
        }
        return 0;
    }

    public int supprimerStatut(StatutEvenement statut) throws DAOException {
        try (Connection connexion = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(DELETE_STATUT_QUERY)) {
            preparedStatement.setInt(1, statut.getId_statut_evenement());
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected;
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e.getCause());
        }
    }

    public StatutEvenement trouverStatutAvecId(int id_statut_evenement) throws DAOException {
        try (Connection connexion = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(FIND_STATUT_QUERY)) {
            preparedStatement.setInt(1, id_statut_evenement);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String description_statut_evenement = resultSet.getString("description_statut_evenement");
                return new StatutEvenement(id_statut_evenement, description_statut_evenement);
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e.getCause());
        }
        return null;
    }

    public List<StatutEvenement> trouverTousStatuts() throws DAOException {
        ArrayList<StatutEvenement> listeStatuts = new ArrayList<>();
        try (Connection connexion = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(FIND_ALL_STATUTS_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int id_statut_evenement = resultSet.getInt("id_statut_evenement");
                String description_statut_evenement = resultSet.getString("description_statut_evenement");
                listeStatuts.add(new StatutEvenement(id_statut_evenement, description_statut_evenement));
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e.getCause());
        }
        return listeStatuts;
    }

    public int modifierStatut(StatutEvenement statut) throws DAOException {
        try (Connection connexion = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(MODIFY_STATUT_QUERY)) {
            preparedStatement.setString(1, statut.getDescription_statut_evenement());
            preparedStatement.setInt(2, statut.getId_statut_evenement());
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected;
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e.getCause());
        }
    }
}
