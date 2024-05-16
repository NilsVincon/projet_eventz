package com.epf.eventz.service;

import com.epf.eventz.dao.EvenementDAO;
import com.epf.eventz.dao.ParticipeDAO;
import com.epf.eventz.model.Evenement;
import com.epf.eventz.model.Participe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParticipeService {

    private final ParticipeDAO participeDAO;

    @Autowired
    public ParticipeService(ParticipeDAO participeDAO) {
        this.participeDAO = participeDAO;
    }

    public void addParticipe(Participe participe){
        participeDAO.save(participe);
    }
    
}
