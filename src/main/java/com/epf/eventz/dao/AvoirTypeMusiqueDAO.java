package com.epf.eventz.dao;

import com.epf.eventz.exception.DAOException;
import com.epf.eventz.model.AvoirTypeMusique;
import com.epf.eventz.persistence.ConnectionManager;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AvoirTypeMusiqueDAO {

    private static final String FIND_EVENEMENTS_BY_TYPE_MUSIQUE_QUERY = "SELECT id_evenement FROM AvoirTypeMusique WHERE id_type_musique=?";
    private static final String FIND_TYPE_MUSIQUE_BY_EVENEMENT_QUERY = "SELECT id_type_musique FROM AvoirTypeMusique WHERE id_evenement=?";
    private static final String CREATE_AVOIR_TYPE_MUSIQUE_QUERY = "INSERT INTO AvoirTypeMusique(id_type_musique, id_evenement) VALUES(?, ?)";
    private static final String DELETE_AVOIR_TYPE_MUSIQUE_QUERY = "DELETE FROM AvoirTypeMusique WHERE id_type_musique=? AND id_evenement=?";

    public List<Integer> trouverEvenementsParTypeMusique(int idTypeMusique) throws DAOException {
        List<Integer> evenements = new ArrayList<>();
        try (Connection connexion = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(FIND_EVENEMENTS_BY_TYPE_MUSIQUE_QUERY)) {
            preparedStatement.setInt(1, idTypeMusique);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                evenements.add(resultSet.getInt("id_evenement"));
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e.getCause());
        }
        return evenements;
    }

    public List<Integer> trouverTypesMusiqueParEvenement(int idEvenement) throws DAOException {
        List<Integer> typesMusique = new ArrayList<>();
        try (Connection connexion = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(FIND_TYPE_MUSIQUE_BY_EVENEMENT_QUERY)) {
            preparedStatement.setInt(1, idEvenement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                typesMusique.add(resultSet.getInt("id_type_musique"));
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e.getCause());
        }
        return typesMusique;
    }

    public void ajouterTypeMusiqueAEvenement(int idTypeMusique, int idEvenement) throws DAOException {
        try (Connection connexion = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(CREATE_AVOIR_TYPE_MUSIQUE_QUERY)) {
            preparedStatement.setInt(1, idTypeMusique);
            preparedStatement.setInt(2, idEvenement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e.getCause());
        }
    }
    public void supprimerTypeMusiqueDeEvenement(int idTypeMusique, int idEvenement) throws DAOException {
        try (Connection connexion = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(DELETE_AVOIR_TYPE_MUSIQUE_QUERY)) {
            preparedStatement.setInt(1, idTypeMusique);
            preparedStatement.setInt(2, idEvenement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e.getCause());
        }
    }
}
