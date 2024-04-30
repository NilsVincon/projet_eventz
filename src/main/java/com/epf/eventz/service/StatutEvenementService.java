package com.epf.eventz.service;

import com.epf.eventz.dao.StatutEvenementDAO;
import com.epf.eventz.exception.DAOException;
import com.epf.eventz.exception.ServiceException;
import com.epf.eventz.model.StatutEvenement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatutEvenementService {

    private StatutEvenementDAO statutEvenementDAO;

    @Autowired
    public StatutEvenementService(StatutEvenementDAO statutEvenementDAO) {
        this.statutEvenementDAO = statutEvenementDAO;
    }

    public void creerStatut(StatutEvenement statut) throws ServiceException {
        statutEvenementDAO.save(statut);
    }

    public void supprimerStatut(StatutEvenement statut) throws ServiceException {

        statutEvenementDAO.delete(statut);
    }

    public Optional<StatutEvenement> trouverStatutAvecId(Long id_statut_evenement) throws ServiceException {

        return statutEvenementDAO.findById(id_statut_evenement);
    }

    public List<StatutEvenement> trouverTousStatuts() throws ServiceException {
        return statutEvenementDAO.findAll();
    }

}
