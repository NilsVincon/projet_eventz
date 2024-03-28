package com.epf.eventz.service;

import com.epf.eventz.dao.PrefererTypeMusiqueDAO;
import com.epf.eventz.exception.DAOException;
import com.epf.eventz.exception.ServiceException;
import com.epf.eventz.model.PrefererTypeMusique;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrefererTypeMusiqueService {

    private PrefererTypeMusiqueDAO prefererTypeMusiqueDAO;

    @Autowired
    public PrefererTypeMusiqueService(PrefererTypeMusiqueDAO prefererTypeMusiqueDAO) {
        this.prefererTypeMusiqueDAO = prefererTypeMusiqueDAO;
    }

    public int creerPrefererTypeMusique(PrefererTypeMusique prefererTypeMusique) throws ServiceException {
        try {
            return prefererTypeMusiqueDAO.creerPrefererTypeMusique(prefererTypeMusique);
        } catch (DAOException e) {
            throw new ServiceException("Erreur création préférence type de musique");
        }
    }

    public int supprimerPrefererTypeMusique(int id_type_musique, int id_utilisateur) throws ServiceException {
        try {
            return prefererTypeMusiqueDAO.supprimerPrefererTypeMusique(id_type_musique, id_utilisateur);
        } catch (DAOException e) {
            throw new ServiceException("Erreur suppression préférence type de musique");
        }
    }

    public List<PrefererTypeMusique> trouverPrefererTypeMusiqueByUtilisateur(int id_utilisateur) throws ServiceException {
        try {
            return prefererTypeMusiqueDAO.trouverPrefererTypeMusiqueByUtilisateur(id_utilisateur);
        } catch (DAOException e) {
            throw new ServiceException("Erreur recherche préférence type de musique par utilisateur");
        }
    }

    public List<PrefererTypeMusique> trouverPrefererTypeMusiqueByTypeMusique(int id_type_musique) throws ServiceException {
        try {
            return prefererTypeMusiqueDAO.trouverPrefererTypeMusiqueByTypeMusique(id_type_musique);
        } catch (DAOException e) {
            throw new ServiceException("Erreur recherche préférence type de musique par type de musique");
        }
    }
}
