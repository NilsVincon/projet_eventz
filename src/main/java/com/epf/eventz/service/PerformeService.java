package com.epf.eventz.service;


import java.util.List;

import com.epf.eventz.dao.PerformeDAO;
import com.epf.eventz.exception.DAOException;
import com.epf.eventz.exception.ServiceException;

import com.epf.eventz.model.Performe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PerformeService {

    private static PerformeDAO performeDAO;
    public static PerformeService instance;

    @Autowired
    public PerformeService(PerformeDAO performeDAO) {
        this.performeDAO = performeDAO;
    }

    public long creerPerforme(Performe performe) throws ServiceException {
        long id_performe = 0;


        try {

            id_performe = performeDAO.creerPerforme(performe);

        } catch (DAOException e) {
            throw new ServiceException("Une erreur a eu lieu lors de la création du vehicule");
        }
        return id_performe;

    }

    public Performe trouverPerformeParId(int id) throws ServiceException {
        try {
            Performe performe =performeDAO.trouverPerformeById(id);

            if (performe != null) {
                return performe;
            }

            throw new ServiceException("Le vehicule n°" + id + " n'a pas été trouvé dans la base de données");
        } catch (DAOException e) {
            throw new ServiceException("Une erreur a eu lieu lors de la récupération du vehicule");
        }

    }

    public static List<Performe> trouverAllPerformes() throws ServiceException {
        try {
            List<Performe> performes = performeDAO.trouverAllPerformes();

            if (performes != null) {
                return performes;
            }

            throw new ServiceException("La liste de tous les vehiculess n'a pas été trouvé dans la base de données");
        } catch (DAOException e) {
            throw new ServiceException("Une erreur a eu lieu lors de la récupération de la liste de vehicules");
        }

    }

    public long supprimerPerforme(Performe performe) throws ServiceException {
        long id_performe = 0;

        try {
            id_performe = performeDAO.supprimerPerforme(performe);
        } catch (DAOException e) {
            throw new ServiceException("Une erreur a eu lieu lors de la suppression du vehicule");
        }
        return id_performe;
    }

    public int compter() throws ServiceException{
        int nb_performe = 0;
        try{
            nb_performe = performeDAO.compterPerformes();
        } catch (DAOException e) {
            throw new ServiceException("Une erreur a eu lieu lors du compte du vehicule");
        }
        return nb_performe;
    }

}
