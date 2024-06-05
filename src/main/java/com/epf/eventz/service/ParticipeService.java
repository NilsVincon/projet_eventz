package com.epf.eventz.service;

import com.epf.eventz.dao.EvenementDAO;
import com.epf.eventz.dao.ParticipeDAO;
import com.epf.eventz.model.Evenement;
import com.epf.eventz.model.Participe;
import com.epf.eventz.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Boolean existsByUtilisateurAndEvenement(Utilisateur utilisateur,Evenement evenement){
        return participeDAO.existsByUtilisateurAndEvenement(utilisateur,evenement);
    }
    public List<Evenement> findEvenementsByUtilisateur(Utilisateur utilisateur) {
        return participeDAO.findEvenementsByUtilisateur(utilisateur);
    }


    public int nbparticipants(Evenement evenement){
        return participeDAO.countParticipantsByEvenement(evenement);
    }


    public List<Utilisateur> findUtilisateurByEvent(Evenement evenement) {
        return participeDAO.findUtilisateurByEvent(evenement);
    }

    public List<Utilisateur> findParticipantsByEvenementAndAmis(Evenement evenement, List<Utilisateur> amis) {
        return participeDAO.findParticipantsByEvenementAndAmis(evenement, amis);
    }
}
