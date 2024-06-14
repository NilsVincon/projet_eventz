package com.epf.eventz.service;

import com.epf.eventz.dao.NoterDAO;
import com.epf.eventz.model.Evenement;
import com.epf.eventz.model.Noter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoterService {

    private final NoterDAO noterDAO;

    @Autowired
    public NoterService(NoterDAO noterDAO){
        this.noterDAO = noterDAO;
    }

    public void addNoter(Noter noter){
        if (noter.getUtilisateur() != null && !noterDAO.existsByEvenementAndUtilisateur(noter.getEvenement(), noter.getUtilisateur())) {
            noterDAO.save(noter);
        }else System.out.println("Une erreur s'est produite dans la sauvegarde de l'avis");
    }

    public List<Noter> findByEvenemenement(Evenement evenement){
        return noterDAO.findAllByEvenement(evenement, Sort.by(Sort.Direction.DESC, "id_noter"));
    }

    public String moyenneEvenement(List<Noter> notes) {
        if (notes == null || notes.isEmpty()) {
            return "0.0";
        }
        float sum = 0;
        for (Noter note : notes) {
            sum += note.getNote();
        }
        float average = sum / notes.size();
        return String.format("%.1f", average);
    }


}
