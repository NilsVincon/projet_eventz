package com.epf.eventz.dao;

import com.epf.eventz.exception.DAOException;
import com.epf.eventz.model.Evenement;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class EvenementDAO{
    private static final String CREER_EVENEMENT_QUERY = "INSERT INTO Evenement (id_statut_evenement,id_type_evenement,nom_evenement,description_evenement,debut_evenement,fin_evenement,adresse_evenement,prix_evenement,nb_place_evenement) VALUES(?,?,?,?,?,?,?,?,?)";
    private static final String SUPPRIMER_EVENEMENT_QUERY = "DELETE FROM Evenement WHERE id_evenement=?";
    private static final String TROUVER_EVENEMENT_QUERY = "SELECT id_statut_evenement,id_type_evenement,nom_evenement,description_evenement,debut_evenement,fin_evenement,adresse_evenement,prix_evenement,nb_place_evenement FROM Evenement WHERE id=?";
    private static final String TROUVER_TOUT_EVENEMENTS_QUERY = "SELECT id_evenement,id_statut_evenement,id_type_evenement,nom_evenement,description_evenement,debut_evenement,fin_evenement,adresse_evenement,prix_evenement,nb_place_evenement FROM Utilisateur";
    private static final String COMPTER_EVENEMENT_QUERY = "SELECT COUNT(*) AS total FROM Evenement";
    private static final String MODIFIER_EVENEMENT_QUERY = "UPDATE Evenement SET id_statut_evenement=?,id_type_evenement=?,nom_evenement=?,description_evenement=?,debut_evenement=?,fin_evenement=?,adresse_evenement=?,prix_evenement=?,nb_place_evenement=? WHERE id_utilisateur=?  ";

    //public int Creer(Evenement evenement) throws DAOException{} TODO AUGUSTIN

    //public int Supprimer(Evenement evenement) throws DAOException{} TODO AUGUSTIN

    //public Evenement TrouverParId(int evenement_id) throws DAOException{} TODO AUGUSTIN

    //public List<Evenement> TrouverTous() throws DAOException{} TODO AUGUSTIN

    //public int Compter() throws DAOException{} TODO AUGUSTIN

}