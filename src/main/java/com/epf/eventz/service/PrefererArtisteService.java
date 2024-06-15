package com.epf.eventz.service;

import com.epf.eventz.dao.PrefererArtisteDAO;
import com.epf.eventz.exception.DAOException;
import com.epf.eventz.exception.ServiceException;
import com.epf.eventz.model.Artiste;
import com.epf.eventz.model.PrefererArtiste;
import com.epf.eventz.model.Utilisateur;
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

    public boolean countPrefereArtisteByAll(Artiste artiste, Utilisateur utilisateur){
        if(prefererArtisteDAO.existsByArtisteAndUtilisateur(artiste,utilisateur)){
            return true;
        }
        return false;
    }

    public PrefererArtiste findByArtisteAndUtilisateur(Artiste artiste, Utilisateur utilisateur) throws ServiceException {
        if (prefererArtisteDAO.findByArtisteAndUtilisateur(artiste, utilisateur).isPresent()) {
            return prefererArtisteDAO.findByArtisteAndUtilisateur(artiste, utilisateur).get();
        }
        return null;
    }
}