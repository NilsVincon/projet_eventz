package com.epf.eventz.service;

import com.epf.eventz.dao.SuivreDAO;
import com.epf.eventz.exception.DAOException;
import com.epf.eventz.exception.ServiceException;
import com.epf.eventz.model.Artiste;
import com.epf.eventz.model.Suivre;
import com.epf.eventz.model.Utilisateur;
import jdk.jshell.execution.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SuivreService {

    private SuivreDAO suivreDAO;

    @Autowired
    public SuivreService(SuivreDAO suivreDAO) {
        this.suivreDAO = suivreDAO;
    }

    public void creerSuivre(Suivre suivre) throws ServiceException {
        suivreDAO.save(suivre);
    }

    public boolean existsParSuiveurEtSuivi(Utilisateur suiveur,Utilisateur suivi){
        return suivreDAO.existsBySuiveurAndSuivi(suiveur,suivi);
    }

    public boolean estAmis(Utilisateur user1,Utilisateur user2){
        return suivreDAO.areFriend(user1,user2);
    }


    public void supprimerSuivre(Suivre suivre) throws ServiceException {
        suivreDAO.delete(suivre);
    }

    public Optional<Suivre> trouverSuivreAvecId(Long idSuivre) throws ServiceException {
        return suivreDAO.findById(idSuivre);
    }

    public List<Suivre> trouverTousSuivres() throws ServiceException {
        return suivreDAO.findAll();
    }

    public int compterSuivres() throws ServiceException {
        return (int) suivreDAO.count();
    }
    public void supprimerParSuiveurEtSuivi(Utilisateur suiveur, Utilisateur suivi) throws ServiceException {
        Suivre suivre = suivreDAO.findBySuiveurAndSuivi(suiveur, suivi);
        if (suivre != null) {
            suivreDAO.delete(suivre);
        } else {
            throw new ServiceException("Suivre not found");
        }
    }


    public void supprimerAbonnement(Utilisateur suiveur, Utilisateur suivi) throws ServiceException {
        try {
            suivreDAO.deleteBySuiveurAndSuivi(suiveur, suivi);
        } catch (Exception e) {
            throw new ServiceException("Erreur lors de la suppression de l'abonnement");
        }
    }

    public List<Utilisateur> findAmisByUtilisateur(Utilisateur utilisateur){
        return suivreDAO.findAmisByUtilisateur(utilisateur);
    }

    public boolean existsBySuiveurAndSuivi(Utilisateur suiveur, Utilisateur suivis){
        return suivreDAO.existsBySuiveurAndSuivi(suiveur, suivis);
    }
}
