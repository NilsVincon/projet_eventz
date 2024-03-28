package com.epf.eventz.service;

import com.epf.eventz.dao.PrefererTypeEvenementDAO;
import com.epf.eventz.exception.DAOException;
import com.epf.eventz.exception.ServiceException;
import com.epf.eventz.model.PrefererTypeEvenement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrefererTypeEvenementService {

    private PrefererTypeEvenementDAO prefererTypeEvenementDAO;

    @Autowired
    public PrefererTypeEvenementService(PrefererTypeEvenementDAO prefererTypeEvenementDAO) {
        this.prefererTypeEvenementDAO = prefererTypeEvenementDAO;
    }

    public int creerPrefererTypeEvenement(PrefererTypeEvenement prefererTypeEvenement) throws ServiceException {
        try {
            return prefererTypeEvenementDAO.creerPrefererTypeEvenement(prefererTypeEvenement);
        } catch (DAOException e) {
            throw new ServiceException("Erreur création préférence type d'événement");
        }
    }

    public int supprimerPrefererTypeEvenement(int id_type_evenement, int id_utilisateur) throws ServiceException {
        try {
            return prefererTypeEvenementDAO.supprimerPrefererTypeEvenement(id_type_evenement, id_utilisateur);
        } catch (DAOException e) {
            throw new ServiceException("Erreur suppression préférence type d'événement");
        }
    }

    public List<PrefererTypeEvenement> trouverPrefererTypeEvenementByUtilisateur(int id_utilisateur) throws ServiceException {
        try {
            return prefererTypeEvenementDAO.trouverPrefererTypeEvenementByUtilisateur(id_utilisateur);
        } catch (DAOException e) {
            throw new ServiceException("Erreur recherche préférence type d'événement par utilisateur");
        }
    }

    public List<PrefererTypeEvenement> trouverPrefererTypeEvenementByTypeEvenement(int id_type_evenement) throws ServiceException {
        try {
            return prefererTypeEvenementDAO.trouverPrefererTypeEvenementByTypeEvenement(id_type_evenement);
        } catch (DAOException e) {
            throw new ServiceException("Erreur recherche préférence type d'événement par type d'événement");
        }
    }
}
