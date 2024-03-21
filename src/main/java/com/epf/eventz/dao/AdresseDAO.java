package com.epf.eventz.dao;

import com.epf.eventz.exception.DAOException;
import com.epf.eventz.model.Adresse;
import com.epf.eventz.persistence.ConnectionManager;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AdresseDAO {

    private static final String CREATE_ADRESSE_QUERY = "INSERT INTO Adresse(numero_adresse, rue_adresse, ville_adresse, code_postal_adresse, pays_adresse, public_adresse) VALUES(?,?,?,?,?,?)";
    private static final String DELETE_ADRESSE_QUERY = "DELETE FROM Adresse WHERE id_adresse=?";
    private static final String FIND_ADRESSE_QUERY = "SELECT id_adresse, numero_adresse, rue_adresse, ville_adresse, code_postal_adresse, pays_adresse, public_adresse FROM Adresse WHERE id_adresse=?";
    private static final String FIND_ALL_ADRESSES_QUERY = "SELECT id_adresse, numero_adresse, rue_adresse, ville_adresse, code_postal_adresse, pays_adresse, public_adresse FROM Adresse";
    private static final String COUNT_ADRESSES_QUERY = "SELECT COUNT(*) AS nb_adresses FROM Adresse";
    private static final String MODIFY_ADRESSE_QUERY = "UPDATE Adresse SET numero_adresse=?, rue_adresse=?, ville_adresse=?, code_postal_adresse=?, pays_adresse=?, public_adresse=? WHERE id_adresse=?";

    public int creerAdresse(Adresse adresse) throws DAOException {
        try (Connection connexion = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(CREATE_ADRESSE_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, adresse.getNumero_adresse());
            preparedStatement.setString(2, adresse.getRue_adresse());
            preparedStatement.setString(3, adresse.getVille_adresse());
            preparedStatement.setInt(4, adresse.getCode_postal_adresse());
            preparedStatement.setString(5, adresse.getPays_adresse());
            preparedStatement.setBoolean(6, adresse.isPublic_adresse());
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

    public int supprimerAdresse(Adresse adresse) throws DAOException {
        try (Connection connexion = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(DELETE_ADRESSE_QUERY)) {
            preparedStatement.setInt(1, adresse.getId_adresse());
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected;
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e.getCause());
        }
    }

    public Adresse trouverAdresseAvecId(int adresse_id) throws DAOException {
        try (Connection connexion = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(FIND_ADRESSE_QUERY)) {
            preparedStatement.setInt(1, adresse_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id_adresse = resultSet.getInt("id_adresse");
                String numero_adresse = resultSet.getString("numero_adresse");
                String rue_adresse = resultSet.getString("rue_adresse");
                String ville_adresse = resultSet.getString("ville_adresse");
                int code_postal_adresse = resultSet.getInt("code_postal_adresse");
                String pays_adresse = resultSet.getString("pays_adresse");
                boolean public_adresse = resultSet.getBoolean("public_adresse");
                return new Adresse(id_adresse, numero_adresse, rue_adresse, ville_adresse, code_postal_adresse, pays_adresse, public_adresse);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e.getCause());
        }
    }

    public List<Adresse> trouverToutesAdresses() throws DAOException {
        List<Adresse> listeAdresses = new ArrayList<>();
        try (Connection connexion = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(FIND_ALL_ADRESSES_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int id_adresse = resultSet.getInt("id_adresse");
                String numero_adresse = resultSet.getString("numero_adresse");
                String rue_adresse = resultSet.getString("rue_adresse");
                String ville_adresse = resultSet.getString("ville_adresse");
                int code_postal_adresse = resultSet.getInt("code_postal_adresse");
                String pays_adresse = resultSet.getString("pays_adresse");
                boolean public_adresse = resultSet.getBoolean("public_adresse");
                Adresse adresse = new Adresse(id_adresse, numero_adresse, rue_adresse, ville_adresse, code_postal_adresse, pays_adresse, public_adresse);
                listeAdresses.add(adresse);
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e.getCause());
        }
        return listeAdresses;
    }

    public int compterAdresses() throws DAOException {
        try (Connection connexion = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(COUNT_ADRESSES_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt("nb_adresses");
            } else {
                return 0;
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e.getCause());
        }
    }

    public int modifierAdresse(Adresse adresse) throws DAOException {
        try (Connection connexion = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(MODIFY_ADRESSE_QUERY)) {
            preparedStatement.setString(1, adresse.getNumero_adresse());
            preparedStatement.setString(2, adresse.getRue_adresse());
            preparedStatement.setString(3, adresse.getVille_adresse());
            preparedStatement.setInt(4, adresse.getCode_postal_adresse());
            preparedStatement.setString(5, adresse.getPays_adresse());
            preparedStatement.setBoolean(6, adresse.isPublic_adresse());
            preparedStatement.setInt(7, adresse.getId_adresse());
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected;
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e.getCause());
        }
    }
}
