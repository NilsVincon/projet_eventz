package com.epf.eventz.service;

import com.epf.eventz.dao.EvenementDAO;
import com.epf.eventz.exception.DAOException;
import com.epf.eventz.exception.ServiceException;
import com.epf.eventz.model.Evenement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EvenementService {
    private EvenementDAO evenementDAO;

    @Autowired
    public EvenementService(EvenementDAO evenementDAO) {
        this.evenementDAO = evenementDAO;
    }

    public long creerEvenement(Evenement evenement) throws ServiceException {
        try {
            return evenementDAO.creerEvenement(evenement);
        } catch (DAOException e) {
            throw new ServiceException("Erreur creation evenement");
        }
    }

    public long supprimerEvenement(Evenement evenement) throws ServiceException {
        try {
            return evenementDAO.supprimerEvenement(evenement);
        } catch (DAOException e) {
            throw new ServiceException("Erreur suppression evenement");
        }
    }

    public Evenement trouverEvenementAvecId(int evenementId) throws ServiceException {
        try {
            return evenementDAO.trouverEvenementAvecId(evenementId);
        } catch (DAOException e) {
            throw new ServiceException("Erreur recherche evenement");
        }
    }

    public List<Evenement> trouverTousEvenements() throws ServiceException {
        try {
            return evenementDAO.trouverTousEvenements();
        } catch (DAOException e) {
            throw new ServiceException("Erreur recherche de tous evenements");
        }
    }

    public int compterEvenements() throws ServiceException {
        try {
            return evenementDAO.compterEvenements();
        } catch (DAOException e) {
            throw new ServiceException("Erreur compte de tous evenements");
        }
    }

    public int modifierEvenement(Evenement evenement) throws ServiceException {
        try {
            return evenementDAO.modifierEvenement(evenement);
        } catch (DAOException e) {
            throw new ServiceException("Erreur modification evenement");
        }
    }
}
