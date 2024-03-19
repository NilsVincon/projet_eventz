package com.epf.eventz.service;

import com.epf.eventz.dao.UtilisateurDAO;
import com.epf.eventz.exception.DAOException;
import com.epf.eventz.model.Utilisateur;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurService {

    private UtilisateurDAO utilisateurDAO;

    public UtilisateurService(UtilisateurDAO utilisateurDAO) {
        this.utilisateurDAO = utilisateurDAO;
    }

    public long createUtilisateur(Utilisateur utilisateur) throws DAOException {
        validateUtilisateur(utilisateur);
        try {
            return utilisateurDAO.creerUtilisateur(utilisateur);
        } catch (DAOException e) {
            throw new DAOException("Error creating utilisateur", e);
        }
    }

    public long deleteUtilisateur(Utilisateur utilisateur) throws DAOException {
        try {
            return utilisateurDAO.supprimerUtilisateur(utilisateur);
        } catch (DAOException e) {
            throw new DAOException("Error deleting utilisateur", e);
        }
    }

    public Utilisateur findUtilisateurById(int utilisateurId) throws DAOException {
        try {
            return utilisateurDAO.trouverUtilisateurAvecId(utilisateurId);
        } catch (DAOException e) {
            throw new DAOException("Error finding utilisateur", e);
        }
    }

    public List<Utilisateur> findAllUtilisateurs() throws DAOException {
        try {
            return utilisateurDAO.trouverTousUtilisateurs();
        } catch (DAOException e) {
            throw new DAOException("Error finding all utilisateurs", e);
        }
    }

    private static void validateUtilisateur(Utilisateur utilisateur) throws DAOException {
        if (utilisateur.getPseudo_utilisateur().isEmpty() || utilisateur.getEmail_utilisateur().isEmpty()) {
            throw new DAOException("Client name and first name cannot be empty." );
        }
    }
}
