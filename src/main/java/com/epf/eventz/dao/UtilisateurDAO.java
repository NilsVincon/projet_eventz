package com.epf.eventz.dao;

import com.epf.eventz.exception.DAOException;
import com.epf.eventz.model.Utilisateur;
import com.epf.eventz.persistence.ConnectionManager;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Repository
public class UtilisateurDAO {

    private static final String CREATE_USER_QUERY = "INSERT INTO Utilisateur(nom_utilisateur,prenom_utilisateur,email_utilisateur,mdp_utilisateur,pseudo_utilisateur,sexe_utilisateur,admin_utilisateur,naissance_utilisateur,description_utilisateur) VALUES(?,?,?,?,?,?,?,?,?)";
    private static final String DELETE_USER_QUERY = "DELETE FROM Utilisateur WHERE id_utilisateur=?";
    private static final String FIND_USER_QUERY = "SELECT nom_utilisateur,prenom_utilisateur,email_utilisateur,mdp_utilisateur,pseudo_utilisateur,sexe_utilisateur,admin_utilisateur,naissance_utilisateur,description_utilisateur FROM Utilisateur WHERE id=?";
    private static final String FIND_ALL_USERS_QUERY = "SELECT id_utilisateur,nom_utilisateur,prenom_utilisateur,email_utilisateur,mdp_utilisateur,pseudo_utilisateur,sexe_utilisateur,admin_utilisateur,naissance_utilisateur,description_utilisateur FROM Utilisateur";
    private static final String COUNT_USERS_QUERY = "SELECT COUNT(*) AS nb_users FROM Utilisateur";
    private static final String MODIFY_USER_QUERY = "UPDATE Utilisateur SET nom_utilisateur=?,prenom_utilisateur=?,email_utilisateur=?,mdp_utilisateur=?,pseudo_utilisateur=?,sexe_utilisateur=?,admin_utilisateur=?,naissance_utilisateur=?,description_utilisateur=? WHERE id_utilisateur=?  ";

    public int createUser(Utilisateur utilisateur) throws DAOException{
        try (Connection connexion = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(CREATE_USER_QUERY);){
            preparedStatement.setString(1, utilisateur.getNom_utilisateur());
            preparedStatement.setString(2, utilisateur.getPrenom_utilisateur());
            preparedStatement.setString(3, utilisateur.getEmail_utilisateur());
            preparedStatement.setString(4, utilisateur.getMdp_utilisateur());
            preparedStatement.setString(5, utilisateur.getPseudo_utilisateur());
            preparedStatement.setString(6, utilisateur.getSexe_utilisateur());
            preparedStatement.setBoolean(7, utilisateur.getAdmin_utilisateur());
            preparedStatement.setDate(8, Date.valueOf(utilisateur.getNaissance_utilisateur()));
            preparedStatement.setString(9, utilisateur.getDescription_utilisateur());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()){
                return resultSet.getInt(1);
            }
        } catch (SQLException e ) {
            throw new DAOException(e.getMessage(),e.getCause());
        }
        return 0;}

    public int deleteUser(Utilisateur utilisateur) throws DAOException{
        try(Connection connexion = ConnectionManager.getConnection();
            PreparedStatement preparedStatement=connexion.prepareStatement(DELETE_USER_QUERY);){
            preparedStatement.setInt(1, utilisateur.getId_utilisateur());
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

    public Utilisateur findUserById(int utilisateur_id) throws DAOException{
        try(Connection connexion = ConnectionManager.getConnection();
            PreparedStatement preparedStatement= connexion.prepareStatement(FIND_USER_QUERY);
            ResultSet resultSet= preparedStatement.executeQuery()){
        preparedStatement.setInt(1, (int) utilisateur_id);
        String nom_utilisateur=resultSet.getString("nom_utilisateur");
        String prenom_utilisateur=resultSet.getString("prenom_utilisateur");
        String email_utilisateur=resultSet.getString("email_utilisateur");
        String mdp_utilisateur=resultSet.getString("mdp_utilisateur");
        String pseudo_utilisateur=resultSet.getString("pseudo_utilisateur");
        String sexe_utilisateur=resultSet.getString("sexe_utilisateur");
        Boolean admin_utilisateur=resultSet.getBoolean("admin_utilisateur");
        LocalDate naissance_utilisateur=resultSet.getDate("naissance_utilisateur").toLocalDate();
        String description_utilisateur=resultSet.getString("description_utilisateur");
        return new Utilisateur(utilisateur_id, nom_utilisateur, prenom_utilisateur, email_utilisateur, mdp_utilisateur, pseudo_utilisateur, sexe_utilisateur, admin_utilisateur, naissance_utilisateur, description_utilisateur);

    } catch (SQLException e){
        throw new DAOException(e.getMessage(),e.getCause());
    }}

    public List<Utilisateur> findAllUsers() throws DAOException{
        ArrayList<Utilisateur> ListUtilisateur = new ArrayList<>();
        try (
                Connection connexion = ConnectionManager.getConnection();
                PreparedStatement preparedStatement= connexion.prepareStatement(FIND_ALL_USERS_QUERY);
                ResultSet resultSet= preparedStatement.executeQuery();
        ) {
            while (resultSet.next()){
                int utilisateur_id = resultSet.getInt("id");
                String nom_utilisateur=resultSet.getString("nom_utilisateur");
                String prenom_utilisateur=resultSet.getString("prenom_utilisateur");
                String email_utilisateur=resultSet.getString("email_utilisateur");
                String mdp_utilisateur=resultSet.getString("mdp_utilisateur");
                String pseudo_utilisateur=resultSet.getString("pseudo_utilisateur");
                String sexe_utilisateur=resultSet.getString("sexe_utilisateur");
                Boolean admin_utilisateur=resultSet.getBoolean("admin_utilisateur");
                LocalDate naissance_utilisateur=resultSet.getDate("naissance_utilisateur").toLocalDate();
                String description_utilisateur=resultSet.getString("description_utilisateur");
                ListUtilisateur.add(new Utilisateur(utilisateur_id, nom_utilisateur, prenom_utilisateur,
                        email_utilisateur, mdp_utilisateur, pseudo_utilisateur, sexe_utilisateur,
                        admin_utilisateur, naissance_utilisateur, description_utilisateur));
            }
        } catch (SQLException e){
            throw new DAOException(e.getMessage(), e.getCause());
        }
        return ListUtilisateur;
    }

    public int countUsers() throws DAOException{
        try(Connection connexion = ConnectionManager.getConnection();
            PreparedStatement preparedStatement= connexion.prepareStatement(COUNT_USERS_QUERY);
            ResultSet resultSet= preparedStatement.executeQuery();){
        if (resultSet.next()) {
            return resultSet.getInt("nb_users");
        }else{
            return (0);
        }
    }
    catch (SQLException e){
        throw new DAOException(e.getMessage(),e.getCause());
    }
    }
}
