package com.epf.eventz.dao;

import com.epf.eventz.exception.DAOException;
import com.epf.eventz.model.Evenement;
import com.epf.eventz.persistence.ConnectionManager;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EvenementDAO{
    private static final String CREATE_EVENEMENT_QUERY = "INSERT INTO Evenement (id_statut_evenement,id_type_evenement,id_adresse_evenement,nom_evenement,description_evenement,debut_evenement,fin_evenement,prix_evenement,nb_place_evenement) VALUES(?,?,?,?,?,?,?,?,?)";
    private static final String DELETE_EVENEMENT_QUERY = "DELETE FROM Evenement WHERE id_evenement=?";
    private static final String FIND_EVENEMENT_QUERY = "SELECT id_statut_evenement,id_type_evenement,id_adresse_evenement,nom_evenement,description_evenement,debut_evenement,fin_evenement,prix_evenement,nb_place_evenement FROM Evenement WHERE id=?";
    private static final String FIND_ALL_EVENEMENTS_QUERY = "SELECT id_evenement,id_statut_evenement,id_type_evenement,id_adresse_evenement,nom_evenement,description_evenement,debut_evenement,fin_evenement,prix_evenement,nb_place_evenement FROM Evenement";
    private static final String COUNT_EVENEMENTS_QUERY = "SELECT COUNT(*) AS total FROM Evenement";
    private static final String MODIFY_EVENEMENT_QUERY = "UPDATE Evenement SET id_statut_evenement=?,id_type_evenement=?,id_adresse_evenement=?,nom_evenement=?,description_evenement=?,debut_evenement=?,fin_evenement=?,prix_evenement=?,nb_place_evenement=? WHERE id_evenement=?  ";
    
    public int creerEvenement(Evenement evenement) throws DAOException{
        try (Connection connexion = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(CREATE_EVENEMENT_QUERY)){
            preparedStatement.setInt(1, evenement.getId_statut_evenement());
            preparedStatement.setInt(2, evenement.getId_type_evenement());
            preparedStatement.setString(3, evenement.getNom_evenement());
            preparedStatement.setString(4, evenement.getDescription_evenement());
            preparedStatement.setDate(5, Date.valueOf(evenement.getDebut_evenement()));
            preparedStatement.setDate(6, Date.valueOf(evenement.getFin_evenement()));
            preparedStatement.setInt(7, evenement.getId_adresse_evenement());
            preparedStatement.setFloat(8, evenement.getPrix_evenement());
            preparedStatement.setInt(9, evenement.getNb_place_evenement());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()){
                return resultSet.getInt(1);
            }
        } catch (SQLException e ) {
            throw new DAOException(e.getMessage(),e.getCause());
        }
        return 0;}
    

    public int deleteEvenement(Evenement evenement) throws DAOException{
        try(Connection connexion = ConnectionManager.getConnection();
            PreparedStatement preparedStatement=connexion.prepareStatement(DELETE_EVENEMENT_QUERY)){
            preparedStatement.setInt(1, evenement.getId_evenement());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()){
                return resultSet.getInt(1);
            }
        } catch (SQLException e){
            throw new DAOException(e.getMessage(),e.getCause());
        }
        return 0;
    }

    public Evenement findEvenementById(int id_evenement) throws DAOException{
        try(Connection connexion = ConnectionManager.getConnection();
            PreparedStatement preparedStatement= connexion.prepareStatement(FIND_EVENEMENT_QUERY);
            ResultSet resultSet= preparedStatement.executeQuery()){
        preparedStatement.setInt(1, (int) id_evenement);
        Integer id_statut_evenement=resultSet.getInt("id_statut_evenement");
        Integer id_type_evenement=resultSet.getInt("id_type_evenement");
            Integer id_adresse_evenement=resultSet.getInt("id_adresse_evenement");
        String nom_evenement=resultSet.getString("nom_evenement");
        String description_evenement=resultSet.getString("description_evenement");
        LocalDate debut_evenement=resultSet.getDate("debut_evenement").toLocalDate();
        LocalDate fin_evenement=resultSet.getDate("fin_evenement").toLocalDate();
        Float prix_evenement=resultSet.getFloat("prix_evenement");
        Integer nb_place_evenement=resultSet.getInt("nb_place_evenement");
        return new Evenement(0,id_statut_evenement,id_type_evenement,id_adresse_evenement,nom_evenement,description_evenement,debut_evenement,fin_evenement,prix_evenement,nb_place_evenement);

    } catch (SQLException e){
        throw new DAOException(e.getMessage(),e.getCause());
    }}

    public List<Evenement> findAllEvenements() throws DAOException{
        ArrayList<Evenement> ListeEvenement = new ArrayList<>();
        try (
                Connection connexion = ConnectionManager.getConnection();
                PreparedStatement preparedStatement= connexion.prepareStatement(FIND_ALL_EVENEMENTS_QUERY);
                ResultSet resultSet= preparedStatement.executeQuery()
        ) {
            while (resultSet.next()){
                int id_evenement = resultSet.getInt("id");
                Integer id_statut_evenement=resultSet.getInt("id_statut_evenement");
                Integer id_type_evenement=resultSet.getInt("id_type_evenement");
                Integer id_adresse_evenement=resultSet.getInt("id_adresse_evenement");
                String nom_evenement=resultSet.getString("nom_evenement");
                String description_evenement=resultSet.getString("description_evenement");
                LocalDate debut_evenement=resultSet.getDate("debut_evenement").toLocalDate();
                LocalDate fin_evenement=resultSet.getDate("fin_evenement").toLocalDate();
                Float prix_evenement=resultSet.getFloat("prix_evenement");
                Integer nb_place_evenement=resultSet.getInt("nb_place_evenement");
                ListeEvenement.add(new Evenement(id_evenement,id_statut_evenement,id_type_evenement,id_adresse_evenement,nom_evenement,
                        description_evenement,debut_evenement,fin_evenement,prix_evenement,nb_place_evenement));
            }
        } catch (SQLException e){
            throw new DAOException(e.getMessage(), e.getCause());
        }
        return ListeEvenement;
    }

    public int countEvenements() throws DAOException{
        try(Connection connexion = ConnectionManager.getConnection();
            PreparedStatement preparedStatement= connexion.prepareStatement(COUNT_EVENEMENTS_QUERY);
            ResultSet resultSet= preparedStatement.executeQuery()){
            if (resultSet.next()) {
                return resultSet.getInt("nb_evenements");
            }else{
                return (0);
            }
        }
        catch (SQLException e){
            throw new DAOException(e.getMessage(),e.getCause());
        }
    }

    public int modifierEvenement(Evenement evenement) throws DAOException {
        try (Connection connexion = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(MODIFY_EVENEMENT_QUERY)) {
            preparedStatement.setInt(1, evenement.getId_statut_evenement());
            preparedStatement.setInt(2, evenement.getId_type_evenement());
            preparedStatement.setInt(3, evenement.getId_adresse_evenement());
            preparedStatement.setString(4, evenement.getNom_evenement());
            preparedStatement.setString(5, evenement.getDescription_evenement());
            preparedStatement.setDate(6, Date.valueOf(evenement.getDebut_evenement()));
            preparedStatement.setDate(7, Date.valueOf(evenement.getFin_evenement()));
            preparedStatement.setFloat(8, evenement.getPrix_evenement());
            preparedStatement.setInt(9, evenement.getNb_place_evenement());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected;
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e.getCause());
        }
    }

}