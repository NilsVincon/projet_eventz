package com.epf.eventz.dao;

import com.epf.eventz.exception.DAOException;
import com.epf.eventz.model.PrefererTypeEvenement;
import com.epf.eventz.persistence.ConnectionManager;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PrefererTypeEvenementDAO {

    private static final String CREATE_PREFERER_TYPE_EVENEMENT_QUERY = "INSERT INTO PrefererTypeEvenement(id_type_evenement, id_utilisateur) VALUES (?, ?)";
    private static final String DELETE_PREFERER_TYPE_EVENEMENT_QUERY = "DELETE FROM PrefererTypeEvenement WHERE id_type_evenement = ? AND id_utilisateur = ?";
    private static final String FIND_PREFERER_TYPE_EVENEMENT_BY_UTILISATEUR_QUERY = "SELECT id_type_evenement, id_utilisateur FROM PrefererTypeEvenement WHERE id_utilisateur = ?";
    private static final String FIND_PREFERER_TYPE_EVENEMENT_BY_TYPE_EVENEMENT_QUERY = "SELECT id_type_evenement, id_utilisateur FROM PrefererTypeEvenement WHERE id_type_evenement = ?";

    public int creerPrefererTypeEvenement(PrefererTypeEvenement prefererTypeEvenement) throws DAOException {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_PREFERER_TYPE_EVENEMENT_QUERY)) {
            preparedStatement.setInt(1, prefererTypeEvenement.getId_type_evenement());
            preparedStatement.setInt(2, prefererTypeEvenement.getId_utilisateur());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new DAOException("Error creating prefered type evenement", e);
        }
        return 0;
    }

    public int supprimerPrefererTypeEvenement(int id_type_evenement, int id_utilisateur) throws DAOException {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PREFERER_TYPE_EVENEMENT_QUERY)) {
            preparedStatement.setInt(1, id_type_evenement);
            preparedStatement.setInt(2, id_utilisateur);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new DAOException("Error deleting prefered type evenement", e);
        }
        return 0;
    }

    public List<PrefererTypeEvenement> trouverPrefererTypeEvenementByUtilisateur(int id_utilisateur) throws DAOException {
        List<PrefererTypeEvenement> preferedTypeEvenements = new ArrayList<>();
        try {
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(FIND_PREFERER_TYPE_EVENEMENT_BY_UTILISATEUR_QUERY);
            ps.setInt(1, id_utilisateur);

            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                PrefererTypeEvenement preferedTypeEvenement = new PrefererTypeEvenement();
                preferedTypeEvenement.setId_type_evenement(resultSet.getInt("id_type_evenement"));
                preferedTypeEvenement.setId_utilisateur(id_utilisateur);
                preferedTypeEvenements.add(preferedTypeEvenement);
            }

            resultSet.close();
            ps.close();
            connection.close();

            return preferedTypeEvenements;
        } catch (SQLException e) {
            throw new DAOException("Error finding prefered type evenements by user", e);
        }
    }

    public List<PrefererTypeEvenement> trouverPrefererTypeEvenementByTypeEvenement(int id_type_evenement) throws DAOException {
        List<PrefererTypeEvenement> preferedTypeEvenements = new ArrayList<>();
        try {
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(FIND_PREFERER_TYPE_EVENEMENT_BY_TYPE_EVENEMENT_QUERY);
            ps.setInt(1, id_type_evenement);

            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                PrefererTypeEvenement preferedTypeEvenement = new PrefererTypeEvenement();
                preferedTypeEvenement.setId_type_evenement(id_type_evenement);
                preferedTypeEvenement.setId_utilisateur(resultSet.getInt("id_utilisateur"));
                preferedTypeEvenements.add(preferedTypeEvenement);
            }

            resultSet.close();
            ps.close();
            connection.close();

            return preferedTypeEvenements;
        } catch (SQLException e) {
            throw new DAOException("Error finding prefered type evenements by type evenement", e);
        }
    }
}
