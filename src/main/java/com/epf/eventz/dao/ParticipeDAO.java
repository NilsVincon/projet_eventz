package com.epf.eventz.dao;

import com.epf.eventz.exception.DAOException;
import com.epf.eventz.persistence.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ParticipeDAO {
    private static final String FIND_EVENEMENTS_BY_UTILISATEUR_QUERY = "SELECT id_evenement FROM Participe WHERE id_utilisateur=?";
    private static final String FIND_UTILISATEURS_BY_EVENEMENT_QUERY = "SELECT id_utilisateur FROM participe WHERE id_evenement=?";
    private static final String CREATE_PARTICIPE_QUERY = "INSERT INTO Participe(id_utilisateur, id_evenement) VALUES(?, ?)";
    private static final String DELETE_PARTICIPE_QUERY = "DELETE FROM Participe WHERE id_utilisateur=? AND id_evenement=?";



    public List<Integer> trouverEvenementsParUtilisateur(int id_utilisateur) throws DAOException {
        List<Integer> evenements = new ArrayList<>();
        try (Connection connexion = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(FIND_EVENEMENTS_BY_UTILISATEUR_QUERY)) {
            preparedStatement.setInt(1, id_utilisateur);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                evenements.add(resultSet.getInt("id_evenement"));
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e.getCause());
        }
        return evenements;
    }

    public List<Integer> trouverUtilisateursParEvenement(int id_evenement) throws DAOException {
        List<Integer> utilisateurs = new ArrayList<>();
        try (Connection connexion = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(FIND_UTILISATEURS_BY_EVENEMENT_QUERY)) {
            preparedStatement.setInt(1, id_evenement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                utilisateurs.add(resultSet.getInt("id_utilisateur"));
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e.getCause());
        }
        return utilisateurs;
    }

    public void ajouterUtilisateurAEvenement(int id_utilisateur, int id_evenement) throws DAOException {
        try (Connection connexion = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(CREATE_PARTICIPE_QUERY)) {
            preparedStatement.setInt(1, id_utilisateur);
            preparedStatement.setInt(2, id_evenement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e.getCause());
        }
    }
    public void supprimerUtilisateurDeEvenement(int id_utilisateur, int id_evenement) throws DAOException {
        try (Connection connexion = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(DELETE_PARTICIPE_QUERY)) {
            preparedStatement.setInt(1, id_utilisateur);
            preparedStatement.setInt(2, id_evenement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e.getCause());
        }
    }
}
