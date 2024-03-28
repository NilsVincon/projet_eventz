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

    public int creerPrefererArtiste(PrefererArtiste prefererArtiste) throws ServiceException {
        try {
            return prefererArtisteDAO.creerPrefererArtiste(prefererArtiste);
        } catch (DAOException e) {
            throw new ServiceException("Erreur création préférence artiste");
        }
    }

    public int supprimerPrefererArtiste(int id_artiste, int id_utilisateur) throws ServiceException {
        try {
            return prefererArtisteDAO.supprimerPrefererArtiste(id_artiste, id_utilisateur);
        } catch (DAOException e) {
            throw new ServiceException("Erreur suppression préférence artiste");
        }
    }

    public List<PrefererArtiste> trouverPrefererArtisteByUtilisateur(int id_utilisateur) throws ServiceException {
        try {
            return prefererArtisteDAO.trouverPrefererArtisteByUtilisateur(id_utilisateur);
        } catch (DAOException e) {
            throw new ServiceException("Erreur recherche préférence artiste par utilisateur");
        }
    }

    public List<PrefererArtiste> trouverPrefererArtisteByArtiste(int id_artiste) throws ServiceException {
        try {
            return prefererArtisteDAO.trouverPrefererArtisteByArtiste(id_artiste);
        } catch (DAOException e) {
            throw new ServiceException("Erreur recherche préférence artiste par artiste");
        }
    }
}