package com.epf.eventz.service;

import com.epf.eventz.dao.TypeMusiqueDAO;
import com.epf.eventz.exception.DAOException;
import com.epf.eventz.exception.ServiceException;
import com.epf.eventz.model.TypeMusique;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeMusiqueService {

    private final TypeMusiqueDAO typeMusiqueDAO;

    @Autowired
    public TypeMusiqueService(TypeMusiqueDAO typeMusiqueDAO) {
        this.typeMusiqueDAO = typeMusiqueDAO;
    }

    public void creerTypeMusique(TypeMusique typeMusique) throws ServiceException {
        typeMusiqueDAO.save(typeMusique);
    }

    public void supprimerTypeMusique(TypeMusique typeMusique) throws ServiceException {
             typeMusiqueDAO.delete(typeMusique);
    }

    public Optional<TypeMusique> trouverTypeMusiqueAvecId(Long idTypeMusique) throws ServiceException {
            return typeMusiqueDAO.findById(idTypeMusique);
    }

    public List<TypeMusique> trouverTousTypeMusiques() throws ServiceException {
            return typeMusiqueDAO.findAll();
    }

/*
    public int modifierTypeMusique(TypeMusique typeMusique) throws ServiceException {
        try {
            return typeMusiqueDAO.modifierTypeMusique(typeMusique);
        } catch (DAOException e) {
            throw new ServiceException("Erreur lors de la modification du type de musique.");
        }
    }

 */
}
