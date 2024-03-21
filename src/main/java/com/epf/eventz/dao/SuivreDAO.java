package com.epf.eventz.dao;

import com.epf.eventz.exception.DAOException;
import com.epf.eventz.model.Suivre;
import com.epf.eventz.persistence.ConnectionManager;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SuivreDAO {

    private static final String CREATE_SUIVRE_QUERY = "INSERT INTO Suivre(id_utilisateur, id_utilisateur_suivie) VALUES(?,?)";
    private static final String DELETE_SUIVRE_QUERY = "DELETE FROM Suivre WHERE id_utilisateur=? AND id_utilisateur_suivie=?";
    private static final String FIND_BY_ID_UTILISATEUR_QUERY = "SELECT id_utilisateur_suivie FROM Suivre WHERE id_utilisateur=?";
    private static final String FIND_BY_ID_UTILISATEUR_SUIVIE_QUERY = "SELECT id_utilisateur FROM Suivre WHERE id_utilisateur_suivie=?";
    private static final String FIND_ALL_SUIVRE_QUERY = "SELECT id_utilisateur, id_utilisateur_suivie FROM Suivre";
    private static final String COUNT_SUIVRE_QUERY = "SELECT COUNT(*) AS nb_suivres FROM Suivre";

    public int creerSuivre(Suivre suivre) throws DAOException {
        try (Connection connexion = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(CREATE_SUIVRE_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, suivre.getId_utilisateur());
            preparedStatement.setInt(2, suivre.getId_utilisateur_suivie());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e.getCause());
        }
        return 0;
    }

    public int supprimerSuivre(Suivre suivre) throws DAOException {
        try (Connection connexion = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(DELETE_SUIVRE_QUERY)) {
            preparedStatement.setInt(1, suivre.getId_utilisateur());
            preparedStatement.setInt(2, suivre.getId_utilisateur_suivie());
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected;
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e.getCause());
        }
    }

    public Suivre trouverSuivreAvecIdUtilisateur(int id_utilisateur) throws DAOException {
        try (Connection connexion = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(FIND_BY_ID_UTILISATEUR_QUERY)) {
            preparedStatement.setInt(1, id_utilisateur);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id_utilisateur_suivie = resultSet.getInt("id_utilisateur_suivie");
                return new Suivre(id_utilisateur, id_utilisateur_suivie);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e.getCause());
        }
    }

    public Suivre trouverSuivreAvecIdUtilisateurSuivie(int id_utilisateur_suivie) throws DAOException {
        try (Connection connexion = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(FIND_BY_ID_UTILISATEUR_SUIVIE_QUERY)) {
            preparedStatement.setInt(1, id_utilisateur_suivie);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id_utilisateur = resultSet.getInt("id_utilisateur");
                return new Suivre(id_utilisateur, id_utilisateur_suivie);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e.getCause());
        }
    }

    public List<Suivre> trouverTousSuivres() throws DAOException {
        List<Suivre> listeSuivres = new ArrayList<>();
        try (Connection connexion = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(FIND_ALL_SUIVRE_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int id_utilisateur = resultSet.getInt("id_utilisateur");
                int id_utilisateur_suivie = resultSet.getInt("id_utilisateur_suivie");
                Suivre suivre = new Suivre(id_utilisateur, id_utilisateur_suivie);
                listeSuivres.add(suivre);
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e.getCause());
        }
        return listeSuivres;
    }

    public int compterSuivres() throws DAOException {
        try (Connection connexion = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(COUNT_SUIVRE_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt("nb_suivres");
            } else {
                return 0;
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e.getCause());
        }
    }
}
