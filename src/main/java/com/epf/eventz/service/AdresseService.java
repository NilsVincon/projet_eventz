package com.epf.eventz.service;

import com.epf.eventz.dao.AdresseDAO;
import com.epf.eventz.exception.DAOException;
import com.epf.eventz.exception.ServiceException;
import com.epf.eventz.model.Adresse;
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

    public void supprimerAdresse(Long adresseId){
        boolean exists = adresseDAO.existsById(adresseId);
        System.out.println("second id "+ adresseId);
        if (!exists){
            System.out.println("bool negatif");
            throw new IllegalStateException(
                    "adresse id"+adresseId+"existe pas"
            );
        }
        System.out.println("bool posss");
        adresseDAO.deleteById(adresseId);;
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

    public void modifierAdresse(Long adresseId, Adresse adresse){
        Adresse adresseToUpdate = adresseDAO.findById(adresseId)
                .orElseThrow(() -> new IllegalStateException("L'adresse avec l'ID " + adresseId + " n'existe pas"));

        adresseToUpdate.setNumero_adresse(adresse.getNumero_adresse());
        adresseToUpdate.setRue_adresse(adresse.getRue_adresse());
        adresseToUpdate.setVille_adresse(adresse.getVille_adresse());
        adresseToUpdate.setCode_postal_adresse(adresse.getCode_postal_adresse());
        adresseToUpdate.setPays_adresse(adresse.getPays_adresse());
        adresseToUpdate.setPublic_adresse(adresse.isPublic_adresse());


        adresseDAO.save(adresseToUpdate);
    }
}