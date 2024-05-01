package com.epf.eventz.service;

import com.epf.eventz.dao.TypeEvenementDAO;
import com.epf.eventz.exception.DAOException;
import com.epf.eventz.exception.ServiceException;

import com.epf.eventz.model.TypeEvenement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeEvenementService {

    private TypeEvenementDAO typeevenementDAO;

    @Autowired
    public TypeEvenementService(TypeEvenementDAO typeevenementDAO) {
        this.typeevenementDAO = typeevenementDAO;
    }

    public void creerTypeEvenement(TypeEvenement typeevenement) throws ServiceException {
        typeevenementDAO.save(typeevenement);
    }

    public void supprimerTypeEvenement(TypeEvenement typeevenement) throws ServiceException {
        typeevenementDAO.delete(typeevenement);
    }

    public Optional<TypeEvenement> trouverUtilisateurAvecId(Long typeevenementID) throws ServiceException {
            return typeevenementDAO.findById(typeevenementID);
    }

    public List<TypeEvenement> trouverTousTypeEvenements() throws ServiceException {

            return typeevenementDAO.findAll();
    }
    public int compterTypeEvenement() throws ServiceException{
        return (int) typeevenementDAO.count();
    }

}
