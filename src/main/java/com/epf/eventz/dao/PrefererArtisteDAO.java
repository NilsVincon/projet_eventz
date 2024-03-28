package com.epf.eventz.dao;

import com.epf.eventz.exception.DAOException;
import com.epf.eventz.model.PrefererArtiste;
import com.epf.eventz.persistence.ConnectionManager;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PrefererArtisteDAO {

    private static final String CREATE_PREFERER_ARTISTE_QUERY = "INSERT INTO PrefererArtiste(id_artiste, id_utilisateur) VALUES (?, ?)";
    private static final String DELETE_PREFERER_ARTISTE_QUERY = "DELETE FROM PrefererArtiste WHERE id_artiste = ? AND id_utilisateur = ?";
    private static final String FIND_PREFERER_ARTISTE_BY_UTILISATEUR_QUERY = "SELECT id_artiste, id_utilisateur FROM PrefererArtiste WHERE  id_utilisateur = ?";
    private static final String FIND_PREFERER_ARTISTE_BY_ARTISTE_QUERY = "SELECT id_artiste, id_utilisateur FROM PrefererArtiste WHERE  id_artiste = ?";

    public int creerPrefererArtiste(PrefererArtiste prefererArtiste) throws DAOException {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_PREFERER_ARTISTE_QUERY)) {
            preparedStatement.setInt(1, prefererArtiste.getId_artiste());
            preparedStatement.setInt(2, prefererArtiste.getId_utilisateur());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new DAOException("Error creating prefered artist", e);
        }
        return 0;
    }

    public int supprimerPrefererArtiste(int id_artiste, int id_utilisateur) throws DAOException {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PREFERER_ARTISTE_QUERY)) {
            preparedStatement.setInt(1, id_artiste);
            preparedStatement.setInt(2, id_utilisateur);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new DAOException("Error deleting prefered artist", e);
        }
        return 0;
    }

    public List<PrefererArtiste> trouverPrefererArtisteByUtilisateur(int id_utilisateur) throws DAOException {
        List<PrefererArtiste> preferedArtists = new ArrayList<>();
        try {
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(FIND_PREFERER_ARTISTE_BY_UTILISATEUR_QUERY);
            ps.setInt(1, id_utilisateur);

            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                PrefererArtiste preferedArtist = new PrefererArtiste();
                preferedArtist.setId_artiste(resultSet.getInt("id_artiste"));
                preferedArtist.setId_utilisateur(id_utilisateur);
                preferedArtists.add(preferedArtist);
            }

            resultSet.close();
            ps.close();
            connection.close();

            return preferedArtists;
        } catch (SQLException e) {
            throw new DAOException("Error finding prefered artists by user", e);
        }
    }
    public List<PrefererArtiste> trouverPrefererArtisteByArtiste(int id_artiste) throws DAOException {
        List<PrefererArtiste> preferedArtists = new ArrayList<>();
        try {
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(FIND_PREFERER_ARTISTE_BY_ARTISTE_QUERY);
            ps.setInt(1, id_artiste);

            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                PrefererArtiste preferedArtist = new PrefererArtiste();
                preferedArtist.setId_artiste(id_artiste);
                preferedArtist.setId_utilisateur(resultSet.getInt("id_utilisateur"));
                preferedArtists.add(preferedArtist);
            }

            resultSet.close();
            ps.close();
            connection.close();

            return preferedArtists;
        } catch (SQLException e) {
            throw new DAOException("Error finding prefered artists by artist", e);
        }
    }


}
