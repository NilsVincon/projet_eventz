package com.epf.eventz.service;

import com.epf.eventz.dao.TypeMusiqueDAO;
import com.epf.eventz.exception.DAOException;
import com.epf.eventz.exception.ServiceException;
import com.epf.eventz.model.TypeMusique;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeMusiqueService {

    private TypeMusiqueDAO typeMusiqueDAO;

    @Autowired
    public TypeMusiqueService(TypeMusiqueDAO typeMusiqueDAO) {
        this.typeMusiqueDAO = typeMusiqueDAO;
    }

    public int creerTypeMusique(TypeMusique typeMusique) throws ServiceException {
        try {
            return typeMusiqueDAO.creerTypeMusique(typeMusique);
        } catch (DAOException e) {
            throw new ServiceException("Erreur lors de la création du type de musique.");
        }
    }

    public int supprimerTypeMusique(TypeMusique typeMusique) throws ServiceException {
        try {
            return typeMusiqueDAO.supprimerTypeMusique(typeMusique);
        } catch (DAOException e) {
            throw new ServiceException("Erreur lors de la suppression du type de musique.");
        }
    }

    public TypeMusique trouverTypeMusiqueAvecId(int idTypeMusique) throws ServiceException {
        try {
            return typeMusiqueDAO.trouverTypeMusiqueAvecId(idTypeMusique);
        } catch (DAOException e) {
            throw new ServiceException("Erreur lors de la recherche du type de musique.");
        }
    }

    public List<TypeMusique> trouverTousTypeMusiques() throws ServiceException {
        try {
            return typeMusiqueDAO.trouverTousTypeMusiques();
        } catch (DAOException e) {
            throw new ServiceException("Erreur lors de la recherche de tous les types de musique.");
        }
    }

    public int modifierTypeMusique(TypeMusique typeMusique) throws ServiceException {
        try {
            return typeMusiqueDAO.modifierTypeMusique(typeMusique);
        } catch (DAOException e) {
            throw new ServiceException("Erreur lors de la modification du type de musique.");
        }
    }
}
