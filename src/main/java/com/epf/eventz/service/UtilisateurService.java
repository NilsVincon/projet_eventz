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

    public void supprimerUtilisateur(Long utilisateurId) throws ServiceException {
        boolean exists = utilisateurDAO.existsById(utilisateurId);
        System.out.println("second id "+ utilisateurId);
        if (!exists){
            System.out.println("bool negatif");
            throw new IllegalStateException(
                    "utilisateur id"+utilisateurId+"existe pas"
            );
        }
        System.out.println("bool posss");
        utilisateurDAO.deleteById(utilisateurId);
    }

    public Optional<Utilisateur> trouverUtilisateurAvecId(Long utilisateurId) throws ServiceException {
        return utilisateurDAO.findById(utilisateurId);
    }

    public List<Utilisateur> trouverTousUtilisateurs() throws ServiceException {
        return (List<Utilisateur>) utilisateurDAO.findAll();
    }

    public int compterUtilisateurs() throws ServiceException {
        return (int) utilisateurDAO.count();
    }

    public void modifierUtilisateur(Long utilisateurId, Utilisateur utilisateur){
        Utilisateur utilisateurToUpdate = utilisateurDAO.findById(utilisateurId)
                .orElseThrow(() -> new IllegalStateException("L'utilisateur avec l'ID " + utilisateurId + " n'existe pas"));

        utilisateurToUpdate.setRole_utilisateur(utilisateur.getRole_utilisateur());
        utilisateurToUpdate.setNom_utilisateur(utilisateur.getNom_utilisateur());
        utilisateurToUpdate.setPrenom_utilisateur(utilisateur.getPrenom_utilisateur());
        utilisateurToUpdate.setEmail_utilisateur(utilisateur.getEmail_utilisateur());
        utilisateurToUpdate.setPassword(utilisateur.getPassword());
        utilisateurToUpdate.setUsername(utilisateur.getUsername());
        utilisateurToUpdate.setSexe_utilisateur(utilisateur.getSexe_utilisateur());
        utilisateurToUpdate.setNaissance_utilisateur(utilisateur.getNaissance_utilisateur());
        utilisateurToUpdate.setDescription_utilisateur(utilisateur.getDescription_utilisateur());



        utilisateurDAO.save(utilisateurToUpdate);
    }

    public Optional<Utilisateur> trouverUtilisateurAvecname(String username) throws ServiceException {
        return utilisateurDAO.findByUsername(username);
    }
    
}
