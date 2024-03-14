package com.epf.eventz.dao;

import com.epf.eventz.exception.DAOException;
import com.epf.eventz.model.Utilisateur;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class UtilisateurDAO {

    private static final String CREER_UTILISATEUR_QUERY = "INSERT INTO Utilisateur(nom_utilisateur,prenom_utilisateur,email_utilisateur,mdp_utilisateur,pseudo_utilisateur,sexe_utilisateur,admin_utilisateur,naissance_utilisateur,description_utilisateur) VALUES(?,?,?,?,?,?,?,?,?)";
    private static final String SUPPRIMER_UTILISATEUR_QUERY = "DELETE FROM Utilisateur WHERE id_utilisateur=?";
    private static final String TROUVER_UTILISATEUR_QUERY = "SELECT nom_utilisateur,prenom_utilisateur,email_utilisateur,mdp_utilisateur,pseudo_utilisateur,sexe_utilisateur,admin_utilisateur,naissance_utilisateur,description_utilisateur FROM Utilisateur WHERE id=?";
    private static final String TROUVER_TOUT_UTILISATEURS_QUERY = "SELECT id_utilisateur,nom_utilisateur,prenom_utilisateur,email_utilisateur,mdp_utilisateur,pseudo_utilisateur,sexe_utilisateur,admin_utilisateur,naissance_utilisateur,description_utilisateur FROM Utilisateur";
    private static final String COMPTER_UTILISATEUR_QUERY = "SELECT COUNT(*) AS total FROM Utilisateur";
    private static final String MODIFIER_UTILISATEUR_QUERY = "UPDATE Utilisateur SET nom_utilisateur=?,prenom_utilisateur=?,email_utilisateur=?,mdp_utilisateur=?,pseudo_utilisateur=?,sexe_utilisateur=?,admin_utilisateur=?,naissance_utilisateur=?,description_utilisateur=? WHERE id_utilisateur=?  ";

    //public int Creer(Utilisateur utilisateur) throws DAOException{} TODO XAVIER

    //public int Supprimer(Utilisateur utilisateur) throws DAOException{} TODO XAVIER

    //public Utilisateur TrouverParId(int utilisateur_id) throws DAOException{} TODO XAVIER

    //public List<Utilisateur> TrouverTous() throws DAOException{} TODO XAVIER

    //public int Compter() throws DAOException{} TODO XAVIER
}
