package com.epf.eventz.service;

import com.epf.eventz.dao.SuivreDAO;
import com.epf.eventz.exception.DAOException;
import com.epf.eventz.exception.ServiceException;
import com.epf.eventz.model.Suivre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuivreService {

    private SuivreDAO suivreDAO;

    @Autowired
    public SuivreService(SuivreDAO suivreDAO) {
        this.suivreDAO = suivreDAO;
    }

    public int creerSuivre(Suivre suivre) throws ServiceException {
        try {
            return suivreDAO.creerSuivre(suivre);
        } catch (DAOException e) {
            throw new ServiceException("Erreur lors de la création du suivi.");
        }
    }

    public int supprimerSuivre(Suivre suivre) throws ServiceException {
        try {
            return suivreDAO.supprimerSuivre(suivre);
        } catch (DAOException e) {
            throw new ServiceException("Erreur lors de la suppression du suivi.");
        }
    }

    public Suivre trouverSuivreAvecIdUtilisateur(int idUtilisateur) throws ServiceException {
        try {
            return suivreDAO.trouverSuivreAvecIdUtilisateur(idUtilisateur);
        } catch (DAOException e) {
            throw new ServiceException("Erreur lors de la recherche du suivi avec l'id de l'utilisateur.");
        }
    }

    public Suivre trouverSuivreAvecIdUtilisateurSuivie(int idUtilisateurSuivi) throws ServiceException {
        try {
            return suivreDAO.trouverSuivreAvecIdUtilisateurSuivie(idUtilisateurSuivi);
        } catch (DAOException e) {
            throw new ServiceException("Erreur lors de la recherche du suivi avec l'id de l'utilisateur suivi.");
        }
    }

    public List<Suivre> trouverTousSuivres() throws ServiceException {
        try {
            return suivreDAO.trouverTousSuivres();
        } catch (DAOException e) {
            throw new ServiceException("Erreur lors de la recherche de tous les suivis.");
        }
    }

    public int compterSuivres() throws ServiceException {
        try {
            return suivreDAO.compterSuivres();
        } catch (DAOException e) {
            throw new ServiceException("Erreur lors du comptage des suivis.");
        }
    }
}
