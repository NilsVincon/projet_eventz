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

    private static final String CREATE_UTILISATEUR_QUERY = "INSERT INTO Utilisateur(nom_utilisateur,prenom_utilisateur,email_utilisateur,mdp_utilisateur,pseudo_utilisateur,sexe_utilisateur,admin_utilisateur,naissance_utilisateur,description_utilisateur) VALUES(?,?,?,?,?,?,?,?,?)";
    private static final String DELETE_UTILISATEUR_QUERY = "DELETE FROM Utilisateur WHERE id_utilisateur=?";
    private static final String FIND_UTILISATEUR_QUERY = "SELECT nom_utilisateur,prenom_utilisateur,email_utilisateur,mdp_utilisateur,pseudo_utilisateur,sexe_utilisateur,admin_utilisateur,naissance_utilisateur,description_utilisateur FROM Utilisateur WHERE id=?";
    private static final String FIND_ALL_UTILISATEURS_QUERY = "SELECT id_utilisateur,nom_utilisateur,prenom_utilisateur,email_utilisateur,mdp_utilisateur,pseudo_utilisateur,sexe_utilisateur,admin_utilisateur,naissance_utilisateur,description_utilisateur FROM Utilisateur";
    private static final String COUNT_UTILISATEURS_QUERY = "SELECT COUNT(*) AS nb_utilisateurs FROM Utilisateur";
    private static final String MODIFY_UTILISATEUR_QUERY = "UPDATE Utilisateur SET nom_utilisateur=?,prenom_utilisateur=?,email_utilisateur=?,mdp_utilisateur=?,pseudo_utilisateur=?,sexe_utilisateur=?,admin_utilisateur=?,naissance_utilisateur=?,description_utilisateur=? WHERE id_utilisateur=?  ";
    private static final String FIND_UTILISATEUR_BY_EMAIL_QUERY = "SELECT id_utilisateur, nom_utilisateur,prenom_utilisateur,email_utilisateur,mdp_utilisateur,sexe_utilisateur,admin_utilisateur,naissance_utilisateur,description_utilisateur FROM Utilisateur WHERE pseudo_utilisateur=?";

    public int creerUtilisateur(Utilisateur utilisateur) throws DAOException{
        try (Connection connexion = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(CREATE_UTILISATEUR_QUERY)){
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

    public int supprimerUtilisateur(Utilisateur utilisateur) throws DAOException{
        try(Connection connexion = ConnectionManager.getConnection();
            PreparedStatement preparedStatement=connexion.prepareStatement(DELETE_UTILISATEUR_QUERY)){
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

    public Utilisateur trouverUtilisateurAvecId(int utilisateur_id) throws DAOException{
        try(Connection connexion = ConnectionManager.getConnection();
            PreparedStatement preparedStatement= connexion.prepareStatement(FIND_UTILISATEUR_QUERY);
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

    public List<Utilisateur> trouverTousUtilisateurs() throws DAOException{
        ArrayList<Utilisateur> ListeUtilisateur = new ArrayList<>();
        try (
                Connection connexion = ConnectionManager.getConnection();
                PreparedStatement preparedStatement= connexion.prepareStatement(FIND_ALL_UTILISATEURS_QUERY);
                ResultSet resultSet= preparedStatement.executeQuery()
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
                ListeUtilisateur.add(new Utilisateur(utilisateur_id, nom_utilisateur, prenom_utilisateur,
                        email_utilisateur, mdp_utilisateur, pseudo_utilisateur, sexe_utilisateur,
                        admin_utilisateur, naissance_utilisateur, description_utilisateur));
            }
        } catch (SQLException e){
            throw new DAOException(e.getMessage(), e.getCause());
        }
        return ListeUtilisateur;
    }

    public int compterUtilisateurs() throws DAOException{
        try(Connection connexion = ConnectionManager.getConnection();
            PreparedStatement preparedStatement= connexion.prepareStatement(COUNT_UTILISATEURS_QUERY);
            ResultSet resultSet= preparedStatement.executeQuery()){
        if (resultSet.next()) {
            return resultSet.getInt("nb_utilisateurs");
        }else{
            return (0);
        }
    }
    catch (SQLException e){
        throw new DAOException(e.getMessage(),e.getCause());
    }
    }
    public int modifierUtilisateur(Utilisateur utilisateur) throws DAOException {
        try (Connection connexion = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(MODIFY_UTILISATEUR_QUERY)) {
            preparedStatement.setString(1, utilisateur.getNom_utilisateur());
            preparedStatement.setString(2, utilisateur.getPrenom_utilisateur());
            preparedStatement.setString(3, utilisateur.getEmail_utilisateur());
            preparedStatement.setString(4, utilisateur.getMdp_utilisateur());
            preparedStatement.setString(5, utilisateur.getPseudo_utilisateur());
            preparedStatement.setString(6, utilisateur.getSexe_utilisateur());
            preparedStatement.setBoolean(7, utilisateur.getAdmin_utilisateur());
            preparedStatement.setDate(8, Date.valueOf(utilisateur.getNaissance_utilisateur()));
            preparedStatement.setString(9, utilisateur.getDescription_utilisateur());
            preparedStatement.setInt(10, utilisateur.getId_utilisateur());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected;
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e.getCause());
        }
    }
    public static Utilisateur trouverUtilisateurAvecEmail(String email_utilisateur) throws DAOException {
        try (
                Connection connexion = ConnectionManager.getConnection();
                PreparedStatement preparedStatement= connexion.prepareStatement(FIND_UTILISATEUR_BY_EMAIL_QUERY);
                ResultSet resultSet= preparedStatement.executeQuery()
        ) {
            Integer id_utilisateur=resultSet.getInt("id_utilisateur");
            String nom_utilisateur=resultSet.getString("nom_utilisateur");
            String prenom_utilisateur=resultSet.getString("prenom_utilisateur");
            String mdp_utilisateur=resultSet.getString("mdp_utilisateur");
            String pseudo_utilisateur=resultSet.getString("pseudo_utilisateur");
            String sexe_utilisateur=resultSet.getString("sexe_utilisateur");
            Boolean admin_utilisateur=resultSet.getBoolean("admin_utilisateur");
            LocalDate naissance_utilisateur=resultSet.getDate("naissance_utilisateur").toLocalDate();
            String description_utilisateur=resultSet.getString("description_utilisateur");
            return new Utilisateur(id_utilisateur, nom_utilisateur, prenom_utilisateur, email_utilisateur, mdp_utilisateur, pseudo_utilisateur, sexe_utilisateur, admin_utilisateur, naissance_utilisateur, description_utilisateur);
        } catch (SQLException e){
            throw new DAOException(e.getMessage(), e.getCause());
        }
    }

}
