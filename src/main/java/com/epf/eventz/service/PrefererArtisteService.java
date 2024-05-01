package com.epf.eventz.service;

import com.epf.eventz.dao.PrefererArtisteDAO;
import com.epf.eventz.exception.DAOException;
import com.epf.eventz.exception.ServiceException;
import com.epf.eventz.model.PrefererArtiste;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrefererArtisteService {

    private PrefererArtisteDAO prefererArtisteDAO;

    @Autowired
    public PrefererArtisteService(PrefererArtisteDAO prefererArtisteDAO) {
        this.prefererArtisteDAO = prefererArtisteDAO;
    }

    public void creerPrefererArtiste(PrefererArtiste prefererArtiste) throws ServiceException {
        prefererArtisteDAO.save(prefererArtiste);
    }

    public void supprimerPrefererArtiste(PrefererArtiste prefererArtiste) throws ServiceException {
        prefererArtisteDAO.delete(prefererArtiste);
    }

//    public List<PrefererArtiste> trouverPrefererArtisteByUtilisateur(int id_utilisateur) throws ServiceException {
//        try {
//            return prefererArtisteDAO.trouverPrefererArtisteByUtilisateur(id_utilisateur);
//        } catch (DAOException e) {
//            throw new ServiceException("Erreur recherche préférence artiste par utilisateur");
//        }
//    }
//
//    public List<PrefererArtiste> trouverPrefererArtisteByArtiste(int id_artiste) throws ServiceException {
//        try {
//            return prefererArtisteDAO.trouverPrefererArtisteByArtiste(id_artiste);
//        } catch (DAOException e) {
//            throw new ServiceException("Erreur recherche préférence artiste par artiste");
//        }
//    }
}