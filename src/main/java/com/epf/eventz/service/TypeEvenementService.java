package com.epf.eventz.service;

import com.epf.eventz.dao.TypeEvenementDAO;
import com.epf.eventz.exception.DAOException;
import com.epf.eventz.exception.ServiceException;

import com.epf.eventz.model.TypeEvenement;
import com.epf.eventz.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeEvenementService {

    private TypeEvenementDAO typeevenementDAO;

    @Autowired
    public TypeEvenementService(TypeEvenementDAO typeevenementDAO) {
        this.typeevenementDAO = typeevenementDAO;
    }

    public long creerTypeEvenement(TypeEvenement typeevenement) throws ServiceException {
        try {
            return typeevenementDAO.creerTypeEvenement(typeevenement);
        } catch (DAOException e) {
            throw new ServiceException("Erreur dans creation TypeEvenement");
        }
    }

    public long supprimerTypeEvenement(TypeEvenement typeevenement) throws ServiceException {
        try {
            return typeevenementDAO.supprimerTypeEvenement(typeevenement);
        } catch (DAOException e) {
            throw new ServiceException("Erreur suppression TypeEvenement");
        }
    }

    public TypeEvenement trouverUtilisateurAvecId(int typeevenementID) throws ServiceException {
        try {
            return typeevenementDAO.trouverTypeEvenementAvecId(typeevenementID);
        } catch (DAOException e) {
            throw new ServiceException("Erreur recherche TypeEvenement");
        }
    }

    public List<TypeEvenement> trouverTousTypeEvenements() throws ServiceException {
        try {
            return typeevenementDAO.trouverTousTypeEvenements();
        } catch (DAOException e) {
            throw new ServiceException("Erreur recherche de tous  TypeEvenement");
        }
    }

    public int modifierTypeEvenement(TypeEvenement typeevenement) throws ServiceException {
        try {
            return typeevenementDAO.modifierTypeEvenement(typeevenement);
        } catch (DAOException e) {
            throw new ServiceException("Erreur modification TypeEvenement");
        }
    }
}
