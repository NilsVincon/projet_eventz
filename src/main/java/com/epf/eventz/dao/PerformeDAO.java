package com.epf.eventz.dao;

import com.epf.eventz.exception.DAOException;
import com.epf.eventz.model.Performe;
import com.epf.eventz.persistence.ConnectionManager;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PerformeDAO {

    private static final String CREATE_PERFORME_QUERY = "INSERT INTO Performe( id_evenement, id_artiste, date_debut_performe, date_fin_performe) VALUES (?, ?, ?, ?)";
    private static final String DELETE_PERFORME_QUERY = "DELETE FROM Performe WHERE id_performe = ?";
    private static final String FIND_PERFORME_BY_ID_QUERY = "SELECT id_performe, id_evenement, id_artiste, date_debut_performe, date_fin_performe FROM Performe WHERE id_performe = ?";
    private static final String FIND_ALL_PERFORMES_QUERY = "SELECT id_perorme, id_evenement, id_artiste, date_debut_performe, date_fin_performe FROM Performe";
    private static final String COUNT_PERFORMES_QUERY = "SELECT COUNT(*) AS nb_performes FROM Performe";
    private static final String FIND_PERFORMES_BY_EVENEMENT_QUERY = "SELECT id_performe, id_evenement, id_artiste, date_debut_performe, date_fin_performe FROM Performe WHERE id_evenement = ? ";

    public int createPerforme(Performe performe) throws DAOException {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_PERFORME_QUERY)) {
            preparedStatement.setInt(1, performe.getId_performe());
            preparedStatement.setInt(2, performe.getId_evenement());
            preparedStatement.setInt(3, performe.getId_artiste());
            preparedStatement.setDate(4, new Date(performe.getDate_debut_performe().getTime()));
            preparedStatement.setDate(5, new Date(performe.getDate_fin_performe().getTime()));
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()){
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new DAOException("Error creating performe", e);
        }
        return 0;
    }

    public int deletePerforme(int id_performe) throws DAOException {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PERFORME_QUERY)) {
            preparedStatement.setInt(1, id_performe);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()){
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new DAOException("Error deleting performe", e);
        }
        return 0;
    }

    public Performe findPerformeById(int id_performe) throws DAOException {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_PERFORME_BY_ID_QUERY)) {
            preparedStatement.setInt(1, id_performe);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Performe(resultSet.getInt("id_performe"),
                        resultSet.getInt("id_evenement"),
                        resultSet.getInt("id_artiste"),
                        resultSet.getDate("date_debut_performe"),
                        resultSet.getDate("date_fin_performe"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DAOException("Error finding performe by ID", e);
        }
    }

    public List<Performe> findAllPerformes() throws DAOException {
        List<Performe> performes = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_PERFORMES_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Performe performe = new Performe(resultSet.getInt("id_performe"),
                        resultSet.getInt("id_evenement"),
                        resultSet.getInt("id_artiste"),
                        resultSet.getDate("date_debut_performe"),
                        resultSet.getDate("date_fin_performe"));
                performes.add(performe);
            }
            return performes;
        } catch (SQLException e) {
            throw new DAOException("Error finding all performes", e);
        }
    }

    public int compterPerformes() throws DAOException{
        try(Connection connexion = ConnectionManager.getConnection();
            PreparedStatement preparedStatement= connexion.prepareStatement(COUNT_PERFORMES_QUERY);
            ResultSet resultSet= preparedStatement.executeQuery()){
            if (resultSet.next()) {
                return resultSet.getInt("nb_performes");
            }else{
                return (0);
            }
        }
        catch (SQLException e){
            throw new DAOException(e.getMessage(),e.getCause());
        }
    }

    public List<Performe> findPerformesByEvenementId(int evenementId) throws DAOException {
        List<Performe> performes = new ArrayList<>();
        try {
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement ps =
                    connection.prepareStatement(FIND_PERFORMES_BY_EVENEMENT_QUERY);
            ps.setInt(1, (int) evenementId);


            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {

                Performe performe = new Performe();
                performe.setId_evenement( evenementId);
                performe.setId_artiste(resultSet.getInt("id_artiste"));
                performe.setDate_debut_performe(resultSet.getDate("Date_debut_performe"));
                performe.setDate_fin_performe(resultSet.getDate("Date_fin_performe"));
                performes.add(performe);
            }

            resultSet.close();
            ps.close();
            connection.close();


            return performes;
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e.getCause());


        }
    }

    public List<Performe> findPerformesByArtisteId(int artisteId) throws DAOException {
        List<Performe> performes = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection("jdbc:h2:~/RentManagerDatabase", "", "");
            PreparedStatement ps =
                    connection.prepareStatement(FIND_PERFORMES_BY_EVENEMENT_QUERY);
            ps.setInt(1,  artisteId);


            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {

                Performe performe = new Performe();
                performe.setId_evenement( artisteId);
                performe.setId_artiste(resultSet.getInt("id_evenement"));
                performe.setDate_debut_performe(resultSet.getDate("Date_debut_performe"));
                performe.setDate_fin_performe(resultSet.getDate("Date_fin_performe"));
                performes.add(performe);
            }

            resultSet.close();
            ps.close();
            connection.close();


            return performes;
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e.getCause());


        }
    }

}
