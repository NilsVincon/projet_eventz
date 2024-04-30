package com.epf.eventz.service;

import com.epf.eventz.dao.EvenementDAO;
import com.epf.eventz.exception.DAOException;
import com.epf.eventz.exception.ServiceException;
import com.epf.eventz.model.Evenement;
import com.epf.eventz.model.Evenement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EvenementService {
    private EvenementDAO evenementDAO;

    @Autowired
    public EvenementService(EvenementDAO evenementDAO) {
        this.evenementDAO = evenementDAO;
    }

    public void addEvenement(Evenement evenement){
        evenementDAO.save(evenement);
    }

    public void deleteEvenement(Evenement evenement){
        evenementDAO.delete(evenement);
    }

    public Optional<Evenement> findEvenementById(long idEvenement) throws ServiceException {
        return evenementDAO.findById(idEvenement);
    }

    public List<Evenement> findAllEvenements() {
        return evenementDAO.findAll();
    }

    public long countEvenements() throws ServiceException {
        return evenementDAO.count();
    }
}
