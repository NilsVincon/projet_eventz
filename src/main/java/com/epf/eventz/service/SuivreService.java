package com.epf.eventz.service;

import com.epf.eventz.dao.SuivreDAO;
import com.epf.eventz.exception.DAOException;
import com.epf.eventz.exception.ServiceException;
import com.epf.eventz.model.Suivre;
import com.epf.eventz.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SuivreService {

    private SuivreDAO suivreDAO;

    @Autowired
    public SuivreService(SuivreDAO suivreDAO) {
        this.suivreDAO = suivreDAO;
    }

    public void creerSuivre(Suivre suivre) throws ServiceException {
        suivreDAO.save(suivre);
    }

    public boolean existsParSuiveurEtSuivi(Utilisateur suiveur,Utilisateur suivi){
        return suivreDAO.existsBySuiveurAndSuivi(suiveur,suivi);
    }

    public void supprimerSuivre(Suivre suivre) throws ServiceException {
        suivreDAO.delete(suivre);
    }

    public Optional<Suivre> trouverSuivreAvecId(Long idSuivre) throws ServiceException {
        return suivreDAO.findById(idSuivre);
    }

    public List<Suivre> trouverTousSuivres() throws ServiceException {
        return suivreDAO.findAll();
    }

    public int compterSuivres() throws ServiceException {
        return (int) suivreDAO.count();
    }
}
