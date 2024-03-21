package com.epf.eventz.dao;

import com.epf.eventz.exception.DAOException;
import com.epf.eventz.model.Artiste;
import com.epf.eventz.persistence.ConnectionManager;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ArtisteDAO {

    private static final String CREATE_ARTISTE_QUERY = "INSERT INTO Artiste(nom_artiste, description_artiste) VALUES(?, ?)";
    private static final String DELETE_ARTISTE_QUERY = "DELETE FROM Artiste WHERE id_artiste=?";
    private static final String FIND_ARTISTE_QUERY = "SELECT nom_artiste, description_artiste FROM Artiste WHERE id_artiste=?";
    private static final String FIND_ALL_ARTISTES_QUERY = "SELECT id_artiste, nom_artiste, description_artiste FROM Artiste";
    private static final String COUNT_ARTISTES_QUERY = "SELECT COUNT(*) AS nb_artistes FROM Artiste";
    private static final String MODIFY_ARTISTE_QUERY = "UPDATE Artiste SET nom_artiste=?, description_artiste=? WHERE id_artiste=?";

    public int creerArtiste(Artiste artiste) throws DAOException {
        try (Connection connexion = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(CREATE_ARTISTE_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, artiste.getNom_artiste());
            preparedStatement.setString(2, artiste.getDescription_artiste());
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

    public int supprimerArtiste(Artiste artiste) throws DAOException {
        try (Connection connexion = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(DELETE_ARTISTE_QUERY)) {
            preparedStatement.setInt(1, artiste.getId_artiste());
            preparedStatement.executeUpdate();
            return artiste.getId_artiste();
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e.getCause());
        }
    }

    public Artiste trouverArtisteAvecId(int id_artiste) throws DAOException {
        try (Connection connexion = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(FIND_ARTISTE_QUERY)) {
            preparedStatement.setInt(1, id_artiste);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String nom_artiste = resultSet.getString("nom_artiste");
                String description_artiste = resultSet.getString("description_artiste");
                return new Artiste(id_artiste, nom_artiste, description_artiste);
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e.getCause());
        }
        return null;
    }

    public List<Artiste> trouverTousArtistes() throws DAOException {
        ArrayList<Artiste> listeArtistes = new ArrayList<>();
        try (Connection connexion = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(FIND_ALL_ARTISTES_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int id_artiste = resultSet.getInt("id_artiste");
                String nom_artiste = resultSet.getString("nom_artiste");
                String description_artiste = resultSet.getString("description_artiste");
                listeArtistes.add(new Artiste(id_artiste, nom_artiste, description_artiste));
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e.getCause());
        }
        return listeArtistes;
    }

    public int compterArtistes() throws DAOException {
        try (Connection connexion = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(COUNT_ARTISTES_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt("nb_artistes");
            } else {
                return 0;
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e.getCause());
        }
    }

    public int modifierArtiste(Artiste artiste) throws DAOException {
        try (Connection connexion = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(MODIFY_ARTISTE_QUERY)) {
            preparedStatement.setString(1, artiste.getNom_artiste());
            preparedStatement.setString(2, artiste.getDescription_artiste());
            preparedStatement.setInt(3, artiste.getId_artiste());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected;
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e.getCause());
        }
    }
}
