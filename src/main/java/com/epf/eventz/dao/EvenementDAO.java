package com.epf.eventz.dao;

import com.epf.eventz.exception.DAOException;
import com.epf.eventz.model.Evenement;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class EvenementDAO{
    private static final String CREATE_EVENT_QUERY = "INSERT INTO Evenement (id_statut_evenement,id_type_evenement,nom_evenement,description_evenement,debut_evenement,fin_evenement,adresse_evenement,prix_evenement,nb_place_evenement) VALUES(?,?,?,?,?,?,?,?,?)";
    private static final String DELETE_EVENT_QUERY = "DELETE FROM Evenement WHERE id_evenement=?";
    private static final String FIND_EVENT_QUERY = "SELECT id_statut_evenement,id_type_evenement,nom_evenement,description_evenement,debut_evenement,fin_evenement,adresse_evenement,prix_evenement,nb_place_evenement FROM Evenement WHERE id=?";
    private static final String FIND_ALL_EVENTS_QUERY = "SELECT id_evenement,id_statut_evenement,id_type_evenement,nom_evenement,description_evenement,debut_evenement,fin_evenement,adresse_evenement,prix_evenement,nb_place_evenement FROM Utilisateur";
    private static final String COUNT_EVENTS_QUERY = "SELECT COUNT(*) AS total FROM Evenement";
    private static final String MODIFY_EVENT_QUERY = "UPDATE Evenement SET id_statut_evenement=?,id_type_evenement=?,nom_evenement=?,description_evenement=?,debut_evenement=?,fin_evenement=?,adresse_evenement=?,prix_evenement=?,nb_place_evenement=? WHERE id_utilisateur=?  ";

    //public int createEvent(Evenement evenement) throws DAOException{} TODO AUGUSTIN

    //public int deleteEvent(Evenement evenement) throws DAOException{} TODO AUGUSTIN

    //public Evenement findEventById(int evenement_id) throws DAOException{} TODO AUGUSTIN

    //public List<Evenement> findAllEvents() throws DAOException{} TODO AUGUSTIN

    //public int countEvents() throws DAOException{} TODO AUGUSTIN

}