package com.epf.eventz.service;

import com.epf.eventz.dao.EvenementDAO;
import com.epf.eventz.exception.DAOException;
import com.epf.eventz.exception.ServiceException;
import com.epf.eventz.model.Evenement;
import com.epf.eventz.model.Evenement;
import com.epf.eventz.model.Evenement;
import com.epf.eventz.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public void deleteEvenement(Long evenementId){
        boolean exists = evenementDAO.existsById(evenementId);
        System.out.println("second id "+ evenementId);
        if (!exists){
            System.out.println("bool negatif");
            throw new IllegalStateException(
                    "evenement id"+evenementId+"existe pas"
            );
        }
        System.out.println("bool posss");
        evenementDAO.deleteById(evenementId);
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
/*
    public Boolean isOrganisateur(Utilisateur utilisateur,Long idevenement){
        return evenementDAO.existsByOrganisateurAndIdEvenement(utilisateur,idevenement);
    }*/

    public void updateEvenement(Long evenementId, Evenement evenement){
        Evenement evenementToUpdate = evenementDAO.findById(evenementId)
                .orElseThrow(() -> new IllegalStateException("L'evenement avec l'ID " + evenementId + " n'existe pas"));

        evenementToUpdate.setNom_evenement(evenement.getNom_evenement());
        evenementToUpdate.setDescription_evenement(evenement.getDescription_evenement());
        evenementToUpdate.setDebut_evenement(evenement.getDebut_evenement());
        evenementToUpdate.setFin_evenement(evenement.getFin_evenement());
        evenementToUpdate.setPrix_evenement(evenement.getPrix_evenement());
        evenementToUpdate.setNb_place_evenement(evenement.getNb_place_evenement());
        evenementDAO.save(evenementToUpdate);
    }

    public List<Evenement> findEvenementsByOrganisateur(Utilisateur organisateur) {
        List<Evenement> evenements = new ArrayList<>();
        for (Evenement evenement:evenementDAO.findAll()){
            if (evenementDAO.existsByOrganisateurAndIdEvenement(organisateur,evenement.getIdEvenement())){
                evenements.add(evenement);
            }
        }
        return evenements;
    }
    public Map<Boolean, List<Evenement>> separerEvenementsParDate(List<Evenement> evenements) {
        LocalDate maintenant = LocalDate.now();
        return evenements.stream()
                .collect(Collectors.partitioningBy(evenement -> evenement.getDebut_evenement().isAfter(maintenant)));

    }

    public List<Evenement> findByOrganisateur(Utilisateur organisateur){
        return evenementDAO.findByOrganisateur(organisateur);
    }
    public Long countEvenementsByOrganisateur(Utilisateur organisateur) {
        return evenementDAO.countByOrganisateur(organisateur);
    }
    
    
}
