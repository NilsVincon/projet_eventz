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

    public void supprimerTypeEvenement(Long typeevenementId) throws ServiceException {
        boolean exists = typeevenementDAO.existsById(typeevenementId);
        if (!exists){
            throw new IllegalStateException(
                    "typeevenement id"+typeevenementId+"existe pas"
            );
        }
        typeevenementDAO.deleteById(typeevenementId);
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

    public void updateTypeevenement(Long typeevenementId, TypeEvenement typeevenement){
        TypeEvenement typeevenementToUpdate = typeevenementDAO.findById(typeevenementId)
                .orElseThrow(() -> new IllegalStateException("L'typeevenement avec l'ID " + typeevenementId + " n'existe pas"));
        typeevenementToUpdate.setDescription_type_evenement(typeevenement.getDescription_type_evenement());
        typeevenementDAO.save(typeevenementToUpdate);
    }

}
