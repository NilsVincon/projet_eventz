package com.epf.eventz.dao;

import com.epf.eventz.exception.DAOException;
import com.epf.eventz.model.TypeMusique;
import com.epf.eventz.persistence.ConnectionManager;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TypeMusiqueDAO {

    private static final String CREATE_TYPE_MUSIQUE_QUERY = "INSERT INTO TypeMusique(id_type_musique, description_type_musique) VALUES (?, ?)";
    private static final String DELETE_TYPE_MUSIQUE_QUERY = "DELETE FROM TypeMusique WHERE id_type_musique=?";
    private static final String FIND_TYPE_MUSIQUE_QUERY = "SELECT id_type_musique, description_type_musique FROM TypeMusique WHERE id_type_musique=?";
    private static final String FIND_ALL_TYPE_MUSIQUES_QUERY = "SELECT id_type_musique, description_type_musique FROM TypeMusique";
    private static final String MODIFY_TYPE_MUSIQUE_QUERY = "UPDATE TypeMusique SET description_type_musique=? WHERE id_type_musique=?";

    public int creerTypeMusique(TypeMusique typeMusique) throws DAOException {
        try (Connection connexion = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(CREATE_TYPE_MUSIQUE_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, typeMusique.getId_type_musique());
            preparedStatement.setString(2, typeMusique.getDescription_type_musique());
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

    public int supprimerTypeMusique(TypeMusique typeMusique) throws DAOException {
        try (Connection connexion = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(DELETE_TYPE_MUSIQUE_QUERY)) {
            preparedStatement.setInt(1, typeMusique.getId_type_musique());
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected;
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e.getCause());
        }
    }

    public TypeMusique trouverTypeMusiqueAvecId(int id_type_musique) throws DAOException {
        try (Connection connexion = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(FIND_TYPE_MUSIQUE_QUERY)) {
            preparedStatement.setInt(1, id_type_musique);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String description_type_musique = resultSet.getString("description_type_musique");
                return new TypeMusique(id_type_musique, description_type_musique);
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e.getCause());
        }
        return null;
    }

    public List<TypeMusique> trouverTousTypeMusiques() throws DAOException {
        ArrayList<TypeMusique> listeTypeMusiques = new ArrayList<>();
        try (Connection connexion = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(FIND_ALL_TYPE_MUSIQUES_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int id_type_musique = resultSet.getInt("id_type_musique");
                String description_type_musique = resultSet.getString("description_type_musique");
                listeTypeMusiques.add(new TypeMusique(id_type_musique, description_type_musique));
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e.getCause());
        }
        return listeTypeMusiques;
    }

    public int modifierTypeMusique(TypeMusique typeMusique) throws DAOException {
        try (Connection connexion = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(MODIFY_TYPE_MUSIQUE_QUERY)) {
            preparedStatement.setString(1, typeMusique.getDescription_type_musique());
            preparedStatement.setInt(2, typeMusique.getId_type_musique());
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected;
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e.getCause());
        }
    }
}

