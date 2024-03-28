package com.epf.eventz.service;

import com.epf.eventz.dao.AdresseDAO;
import com.epf.eventz.exception.DAOException;
import com.epf.eventz.exception.ServiceException;
import com.epf.eventz.model.Adresse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdresseService {

    private AdresseDAO adresseDAO;

    @Autowired
    public AdresseService(AdresseDAO adresseDAO) {
        this.adresseDAO = adresseDAO;
    }

    public int creerAdresse(Adresse adresse) throws ServiceException {
        try {
            return adresseDAO.creerAdresse(adresse);
        } catch (DAOException e) {
            throw new ServiceException("Erreur lors de la création de l'adresse.");
        }
    }

    public int supprimerAdresse(Adresse adresse) throws ServiceException {
        try {
            return adresseDAO.supprimerAdresse(adresse);
        } catch (DAOException e) {
            throw new ServiceException("Erreur lors de la suppression de l'adresse.");
        }
    }

    public Adresse trouverAdresseAvecId(int idAdresse) throws ServiceException {
        try {
            return adresseDAO.trouverAdresseAvecId(idAdresse);
        } catch (DAOException e) {
            throw new ServiceException("Erreur lors de la recherche de l'adresse.");
        }
    }

    public List<Adresse> trouverToutesAdresses() throws ServiceException {
        try {
            return adresseDAO.trouverToutesAdresses();
        } catch (DAOException e) {
            throw new ServiceException("Erreur lors de la recherche de toutes les adresses.");
        }
    }

    public int compterAdresses() throws ServiceException {
        try {
            return adresseDAO.compterAdresses();
        } catch (DAOException e) {
            throw new ServiceException("Erreur lors du comptage des adresses.");
        }
    }

    public int modifierAdresse(Adresse adresse) throws ServiceException {
        try {
            return adresseDAO.modifierAdresse(adresse);
        } catch (DAOException e) {
            throw new ServiceException("Erreur lors de la modification de l'adresse.");
        }
    }
}
