package com.epf.eventz.service;

import com.epf.eventz.dao.EvenementDAO;
import com.epf.eventz.dao.JouerDAO;
import com.epf.eventz.model.Evenement;
import com.epf.eventz.model.Jouer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JouerService {

    private JouerDAO jouerDAO;

    @Autowired
    public JouerService(EvenementDAO evenementDAO) {
        this.jouerDAO = jouerDAO;
    }

    public void addJouer(Jouer jouer){
        jouerDAO.save(jouer);
    }
}
