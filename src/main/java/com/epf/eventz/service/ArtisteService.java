package com.epf.eventz.service;

import com.epf.eventz.dao.ArtisteDAO;
import com.epf.eventz.exception.DAOException;
import com.epf.eventz.exception.ServiceException;
import com.epf.eventz.model.Artiste;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ArtisteService {
    private ArtisteDAO artisteDAO;

    @Autowired
    public ArtisteService(ArtisteDAO artisteDAO) {
        this.artisteDAO = artisteDAO;
    }

    public long creerArtiste(Artiste artiste) throws ServiceException {
        try {
            return artisteDAO.creerArtiste(artiste);
        } catch (DAOException e) {
            throw new ServiceException("Erreur creation artiste");
        }
    }

    public long supprimerArtiste(Artiste artiste) throws ServiceException {
        try {
            return artisteDAO.supprimerArtiste(artiste);
        } catch (DAOException e) {
            throw new ServiceException("Erreur suppression artiste");
        }
    }

    public Artiste trouverArtisteAvecId(int artisteId) throws ServiceException {
        try {
            return artisteDAO.trouverArtisteAvecId(artisteId);
        } catch (DAOException e) {
            throw new ServiceException("Erreur recherche artiste");
        }
    }

    public List<Artiste> trouverTousArtistes() throws ServiceException {
        try {
            return artisteDAO.trouverTousArtistes();
        } catch (DAOException e) {
            throw new ServiceException("Erreur recherche de tous les artistes");
        }
    }

    public int compterArtistes() throws ServiceException {
        try {
            return artisteDAO.compterArtistes();
        } catch (DAOException e) {
            throw new ServiceException("Erreur compte de tous les artistes");
        }
    }

    public int modifierArtiste(Artiste artiste) throws ServiceException {
        try {
            return artisteDAO.modifierArtiste(artiste);
        } catch (DAOException e) {
            throw new ServiceException("Erreur modification artiste");
        }
    }
}
