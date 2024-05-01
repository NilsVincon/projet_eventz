package com.epf.eventz.service;

import com.epf.eventz.dao.UtilisateurDAO;
import com.epf.eventz.exception.DAOException;
import com.epf.eventz.exception.ServiceException;

import com.epf.eventz.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService {

    private static UtilisateurDAO utilisateurDAO;

    @Autowired
    public UtilisateurService(UtilisateurDAO utilisateurDAO) {
        this.utilisateurDAO = utilisateurDAO;
    }

    public void creerUtilisateur(Utilisateur utilisateur) throws ServiceException {
         utilisateurDAO.save(utilisateur);
    }

    public void supprimerUtilisateur(Utilisateur utilisateur) throws ServiceException {
        utilisateurDAO.delete(utilisateur);
    }

    public Optional<Utilisateur> trouverUtilisateurAvecId(Long utilisateurId) throws ServiceException {
        return utilisateurDAO.findById(utilisateurId);
    }

    public List<Utilisateur> trouverTousUtilisateurs() throws ServiceException {
        return utilisateurDAO.findAll();
    }

    public Long compterUtilisateurs() throws ServiceException {
        return utilisateurDAO.count();
    }
}
