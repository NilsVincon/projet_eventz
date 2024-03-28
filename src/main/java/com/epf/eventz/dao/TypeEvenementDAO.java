package com.epf.eventz.dao;

import com.epf.eventz.exception.DAOException;
import com.epf.eventz.model.TypeEvenement;
import com.epf.eventz.persistence.ConnectionManager;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TypeEvenementDAO {

    private static final String CREATE_TYPE_EVENEMENT_QUERY = "INSERT INTO TypeEvenement(id_type_evenement, description_type_evenement) VALUES (?, ?)";
    private static final String DELETE_TYPE_EVENEMENT_QUERY = "DELETE FROM TypeEvenement WHERE id_type_evenement=?";
    private static final String FIND_TYPE_EVENEMENT_QUERY = "SELECT id_type_evenement, description_type_evenement FROM TypeEvenement WHERE id_type_evenement=?";
    private static final String FIND_ALL_TYPE_EVENEMENTS_QUERY = "SELECT id_type_evenement, description_type_evenement FROM TypeEvenement";
    private static final String MODIFY_TYPE_EVENEMENT_QUERY = "UPDATE TypeEvenement SET description_type_evenement=? WHERE id_type_evenement=?";

    public int creerTypeEvenement(TypeEvenement typeEvenement) throws DAOException {
        try (Connection connexion = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(CREATE_TYPE_EVENEMENT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, typeEvenement.getId_type_evenement());
            preparedStatement.setString(2, typeEvenement.getDescription_type_evenement());
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

    public int supprimerTypeEvenement(TypeEvenement typeEvenement) throws DAOException {
        try (Connection connexion = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(DELETE_TYPE_EVENEMENT_QUERY)) {
            preparedStatement.setInt(1, typeEvenement.getId_type_evenement());
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected;
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e.getCause());
        }
    }

    public TypeEvenement trouverTypeEvenementAvecId(int id_type_evenement) throws DAOException {
        try (Connection connexion = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(FIND_TYPE_EVENEMENT_QUERY)) {
            preparedStatement.setInt(1, id_type_evenement);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String description_type_evenement = resultSet.getString("description_type_evenement");
                return new TypeEvenement(id_type_evenement, description_type_evenement);
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e.getCause());
        }
        return null;
    }

    public List<TypeEvenement> trouverTousTypeEvenements() throws DAOException {
        ArrayList<TypeEvenement> listeTypeEvenements = new ArrayList<>();
        try (Connection connexion = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(FIND_ALL_TYPE_EVENEMENTS_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int id_type_evenement = resultSet.getInt("id_type_evenement");
                String description_type_evenement = resultSet.getString("description_type_evenement");
                listeTypeEvenements.add(new TypeEvenement(id_type_evenement, description_type_evenement));
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e.getCause());
        }
        return listeTypeEvenements;
    }

    public int modifierTypeEvenement(TypeEvenement typeEvenement) throws DAOException {
        try (Connection connexion = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(MODIFY_TYPE_EVENEMENT_QUERY)) {
            preparedStatement.setString(1, typeEvenement.getDescription_type_evenement());
            preparedStatement.setInt(2, typeEvenement.getId_type_evenement());
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected;
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e.getCause());
        }
    }
}
