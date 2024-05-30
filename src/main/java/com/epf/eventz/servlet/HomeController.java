package com.epf.eventz.servlet;

import com.epf.eventz.model.*;
import com.epf.eventz.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequestMapping("/eventz")
public class HomeController {

    @Autowired
    private EvenementService evenementService;

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private ParticipeService participeService;



    @GetMapping("/home")
    public String listEvenements(Model model) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Boolean connecte = false;
            if (!authentication.getName().equals("anonymousUser")) {
                connecte = true;
                Optional<Utilisateur> utilisateurOptional = utilisateurService.trouverUtilisateurAvecname(authentication.getName());
                if(utilisateurOptional.isPresent()){
                    Utilisateur utilisateur = utilisateurOptional.get();

                    List<Evenement> evenements = participeService.findEvenementsByUtilisateur(utilisateur);
                    model.addAttribute("evenementsparticipe", evenements);

                    model.addAttribute("username", utilisateur.getUsername());
                  //  List<Evenement> evenements = participeService.findEvenementsByUtilisateur(utilisateur);
                  ///  model.addAttribute("evenementsparticipe", evenements);


                }
            }
            model.addAttribute("connecte", connecte);
            List<Evenement> evenements = evenementService.findAllEvenements();
            model.addAttribute("evenements", evenements);
            Set<String> uniqueEventTypes = evenements.stream()
                    .map(e -> e.getTypeEvenement().getDescription_type_evenement())
                    .collect(Collectors.toSet());
            model.addAttribute("uniqueEventTypes", uniqueEventTypes);
            model.addAttribute("profileOptions", List.of("Profil", "Amis", "Paramètres", "Déconnexion"));
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }
        return "home";
    }
}