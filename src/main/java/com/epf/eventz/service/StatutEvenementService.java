package com.epf.eventz.service;

import com.epf.eventz.dao.StatutEvenementDAO;
import com.epf.eventz.exception.DAOException;
import com.epf.eventz.exception.ServiceException;
import com.epf.eventz.model.StatutEvenement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatutEvenementService {

    private StatutEvenementDAO statutEvenementDAO;

    @Autowired
    public StatutEvenementService(StatutEvenementDAO statutEvenementDAO) {
        this.statutEvenementDAO = statutEvenementDAO;
    }

    public int creerStatut(StatutEvenement statut) throws ServiceException {
        try {
            return statutEvenementDAO.creerStatut(statut);
        } catch (DAOException e) {
            throw new ServiceException("Erreur lors de la création du statut.");
        }
    }

    public int supprimerStatut(StatutEvenement statut) throws ServiceException {
        try {
            return statutEvenementDAO.supprimerStatut(statut);
        } catch (DAOException e) {
            throw new ServiceException("Erreur lors de la suppression du statut.");
        }
    }

    public StatutEvenement trouverStatutAvecId(int id_statut_evenement) throws ServiceException {
        try {
            return statutEvenementDAO.trouverStatutAvecId(id_statut_evenement);
        } catch (DAOException e) {
            throw new ServiceException("Erreur lors de la recherche du statut.");
        }
    }

    public List<StatutEvenement> trouverTousStatuts() throws ServiceException {
        try {
            return statutEvenementDAO.trouverTousStatuts();
        } catch (DAOException e) {
            throw new ServiceException("Erreur lors de la recherche de tous les statuts.");
        }
    }

    public int modifierStatut(StatutEvenement statut) throws ServiceException {
        try {
            return statutEvenementDAO.modifierStatut(statut);
        } catch (DAOException e) {
            throw new ServiceException("Erreur lors de la modification du statut.");
        }
    }
}
