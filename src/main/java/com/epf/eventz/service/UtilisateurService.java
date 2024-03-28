package com.epf.eventz.service;

import com.epf.eventz.dao.UtilisateurDAO;
import com.epf.eventz.exception.DAOException;
import com.epf.eventz.exception.ServiceException;

import com.epf.eventz.model.TypeEvenement;
import com.epf.eventz.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurService {

    private UtilisateurDAO utilisateurDAO;

    @Autowired
    public UtilisateurService(UtilisateurDAO utilisateurDAO) {
        this.utilisateurDAO = utilisateurDAO;
    }

    public long creerUtilisateur(Utilisateur utilisateur) throws ServiceException {
        try {
            return utilisateurDAO.creerUtilisateur(utilisateur);
        } catch (DAOException e) {
            throw new ServiceException("Erreur creation utilisateur");
        }
    }

    public long supprimerUtilisateur(Utilisateur utilisateur) throws ServiceException {
        try {
            return utilisateurDAO.supprimerUtilisateur(utilisateur);
        } catch (DAOException e) {
            throw new ServiceException("Erreur suppression utilisateur");
        }
    }

    public Utilisateur trouverUtilisateurAvecId(int utilisateurId) throws ServiceException {
        try {
            return utilisateurDAO.trouverUtilisateurAvecId(utilisateurId);
        } catch (DAOException e) {
            throw new ServiceException("Erreur recherche utilisateur");
        }
    }

    public List<Utilisateur> trouverTousUtilisateurs() throws ServiceException {
        try {
            return utilisateurDAO.trouverTousUtilisateurs();
        } catch (DAOException e) {
            throw new ServiceException("Erreur recherche de tous utilisateurs");
        }
    }

    public int compterUtilisateurs() throws ServiceException {
        try {
            return utilisateurDAO.compterUtilisateurs();
        } catch (DAOException e) {
            throw new ServiceException("Erreur compte de tous utilisateurs");
        }
    }

    public int modifierUtilisateur(Utilisateur utilisateur) throws ServiceException {
        try {
            return utilisateurDAO.modifierUtilisateur(utilisateur);
        } catch (DAOException e) {
            throw new ServiceException("Erreur modification utilisateur");
        }
    }
}
