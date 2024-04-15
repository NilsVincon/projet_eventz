package com.epf.eventz.service;

import com.epf.eventz.dao.AdresseDAO;
import com.epf.eventz.exception.DAOException;
import com.epf.eventz.exception.ServiceException;
import com.epf.eventz.model.Adresse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdresseService {

    private final AdresseDAO adresseDAO;

    @Autowired
    public AdresseService(AdresseDAO adresseDAO) {
        this.adresseDAO = adresseDAO;
    }

    public void creerAdresse(Adresse adresse){
            adresseDAO.save(adresse);
    }

    public void supprimerAdresse(Adresse adresse){
            adresseDAO.delete(adresse);
    }

    public Optional<Adresse> trouverAdresseAvecId(long idAdresse) throws ServiceException {
            return adresseDAO.findById(idAdresse);
    }

    public List<Adresse> trouverToutesAdresses() {
        return adresseDAO.findAll();
    }

    public long compterAdresses() throws ServiceException {
        return adresseDAO.count();
    }

    /*public void modifierAdresse(Adresse adresse) throws ServiceException {
        try {
            adresseDAO.modifierAdresse(adresse);
        } catch (DAOException e) {
            throw new ServiceException("Erreur lors de la modification de l'adresse.");
        }
    }*/
}