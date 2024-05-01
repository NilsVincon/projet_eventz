package com.epf.eventz.servlet;
import com.epf.eventz.exception.ServiceException;
import com.epf.eventz.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Controller
public class HomeAdminController {
    private final ArtisteService artisteService;
    private final EvenementService evenementService;
    private final UtilisateurService utilisateurService;
    private final AdresseService adresseService;
    private final TypeEvenementService typeEvenementService;
    private final TypeMusiqueService typeMusiqueService;

    @Autowired

    public HomeAdminController(ArtisteService artisteService, EvenementService evenementService, UtilisateurService utilisateurService, AdresseService adresseService, TypeEvenementService typeEvenementService, TypeMusiqueService typeMusiqueService) {this.artisteService = artisteService;this.evenementService = evenementService;this.utilisateurService = utilisateurService;this.adresseService = adresseService;this.typeEvenementService = typeEvenementService;this.typeMusiqueService = typeMusiqueService;}

    @GetMapping("/homeadmin")
    public String gethome(Model model){
        try {
            ArrayList<Objet> objets = getObjets();
            model.addAttribute("items", objets);
        }
        catch (Exception e){
        }
        return "homeadmin";
    }

    private ArrayList<Objet> getObjets() {
        ArrayList<Objet> objets = new ArrayList<>();
        try {
            objets.add(new Objet("artiste", artisteService.countArtistes()));
            objets.add(new Objet("evenement", (int) evenementService.countEvenements()));
            objets.add(new Objet("utilisateur", utilisateurService.compterUtilisateurs()));
            objets.add(new Objet("adresse", adresseService.compterAdresses()));
            objets.add(new Objet("typeevenement", typeEvenementService.compterTypeEvenement()));
            objets.add(new Objet("typemusique", typeMusiqueService.compterTypeMusique()));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        return objets;
    }
    private static class Objet {
        private String nom;
        private int nbIterations;

        public Objet(String nom, int nbIterations) {
            this.nom = nom;
            this.nbIterations = nbIterations;
        }

        public String getNom() {
            return nom;
        }

        public int getNbIterations() {
            return nbIterations;
        }
    }
}
