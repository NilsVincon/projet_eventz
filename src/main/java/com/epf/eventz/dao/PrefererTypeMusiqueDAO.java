package com.epf.eventz.dao;

import com.epf.eventz.exception.DAOException;
import com.epf.eventz.model.PrefererTypeMusique;
import com.epf.eventz.persistence.ConnectionManager;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PrefererTypeMusiqueDAO {

    private static final String CREATE_PREFERER_TYPE_MUSIQUE_QUERY = "INSERT INTO PrefererTypeMusique(id_type_musique, id_utilisateur) VALUES (?, ?)";
    private static final String DELETE_PREFERER_TYPE_MUSIQUE_QUERY = "DELETE FROM PrefererTypeMusique WHERE id_type_musique = ? AND id_utilisateur = ?";
    private static final String FIND_PREFERER_TYPE_MUSIQUE_BY_UTILISATEUR_QUERY = "SELECT id_type_musique, id_utilisateur FROM PrefererTypeMusique WHERE id_utilisateur = ?";
    private static final String FIND_PREFERER_TYPE_MUSIQUE_BY_TYPE_MUSIQUE_QUERY = "SELECT id_type_musique, id_utilisateur FROM PrefererTypeMusique WHERE id_type_musique = ?";

    public int creerPrefererTypeMusique(PrefererTypeMusique prefererTypeMusique) throws DAOException {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_PREFERER_TYPE_MUSIQUE_QUERY)) {
            preparedStatement.setInt(1, prefererTypeMusique.getId_type_musique());
            preparedStatement.setInt(2, prefererTypeMusique.getId_utilisateur());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new DAOException("Error creating prefered type musique", e);
        }
        return 0;
    }

    public int supprimerPrefererTypeMusique(int id_type_musique, int id_utilisateur) throws DAOException {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PREFERER_TYPE_MUSIQUE_QUERY)) {
            preparedStatement.setInt(1, id_type_musique);
            preparedStatement.setInt(2, id_utilisateur);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new DAOException("Error deleting prefered type musique", e);
        }
        return 0;
    }

    public List<PrefererTypeMusique> trouverPrefererTypeMusiqueByUtilisateur(int id_utilisateur) throws DAOException {
        List<PrefererTypeMusique> preferedTypeMusiques = new ArrayList<>();
        try {
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(FIND_PREFERER_TYPE_MUSIQUE_BY_UTILISATEUR_QUERY);
            ps.setInt(1, id_utilisateur);

            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                PrefererTypeMusique preferedTypeMusique = new PrefererTypeMusique();
                preferedTypeMusique.setId_type_musique(resultSet.getInt("id_type_musique"));
                preferedTypeMusique.setId_utilisateur(id_utilisateur);
                preferedTypeMusiques.add(preferedTypeMusique);
            }

            resultSet.close();
            ps.close();
            connection.close();

            return preferedTypeMusiques;
        } catch (SQLException e) {
            throw new DAOException("Error finding prefered type musiques by user", e);
        }
    }

    public List<PrefererTypeMusique> trouverPrefererTypeMusiqueByTypeMusique(int id_type_musique) throws DAOException {
        List<PrefererTypeMusique> preferedTypeMusiques = new ArrayList<>();
        try {
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(FIND_PREFERER_TYPE_MUSIQUE_BY_TYPE_MUSIQUE_QUERY);
            ps.setInt(1, id_type_musique);

            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                PrefererTypeMusique preferedTypeMusique = new PrefererTypeMusique();
                preferedTypeMusique.setId_type_musique(id_type_musique);
                preferedTypeMusique.setId_utilisateur(resultSet.getInt("id_utilisateur"));
                preferedTypeMusiques.add(preferedTypeMusique);
            }

            resultSet.close();
            ps.close();
            connection.close();

            return preferedTypeMusiques;
        } catch (SQLException e) {
            throw new DAOException("Error finding prefered type musiques by type musique", e);
        }
    }
}
