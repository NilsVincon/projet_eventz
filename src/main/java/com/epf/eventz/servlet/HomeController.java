package com.epf.eventz.servlet;

import com.epf.eventz.model.*;
import com.epf.eventz.service.*;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
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

    @Autowired
    private ArtisteService artisteService;

    @Autowired
    private TypeEvenementService typeEvenementService;



    @GetMapping("/home")
    public String listEvenements(Model model, HttpSession session) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (!authentication.getName().equals("anonymousUser")) {
                Optional<Utilisateur> utilisateurOptional = utilisateurService.trouverUtilisateurAvecname(authentication.getName());
                if(utilisateurOptional.isPresent()){
                    Utilisateur utilisateur = utilisateurOptional.get();

                    boolean isFirstLogin = false;
                    if (session.getAttribute("firstLogin") == null) {
                        isFirstLogin = true;
                        session.setAttribute("firstLogin", true);
                    }
                    log.info("is first connexion ? : "+isFirstLogin);
                    List<Evenement> evenements = participeService.findEvenementsByUtilisateur(utilisateur);
                    model.addAttribute("evenementsparticipe", evenements);
                    model.addAttribute("username", utilisateur.getUsername());
                    model.addAttribute("connexion", isFirstLogin);
                    List<Evenement> listEvenementSearchNavbar = evenementService.findAllEvenements();
                    List<Artiste> listArtisteearchNavbar = artisteService.findAllArtistes();
                    List<Utilisateur> listeUtilisateurSearchNavbar = utilisateurService.trouverTousUtilisateurs();
                    model.addAttribute("listEvenementSearchNavbar", listEvenementSearchNavbar);
                    model.addAttribute("listArtisteSearchNavbar", listArtisteearchNavbar);
                    model.addAttribute("listUtilisateurSearchNavbar", listeUtilisateurSearchNavbar );
                }
            }
            List<Evenement> evenements = evenementService.findAllEvenements();
            LocalDate now = LocalDate.now();
            evenements.stream()
                    .filter(evenement -> evenement.getDebut_evenement().isAfter(now))
                    .collect(Collectors.toList());
            model.addAttribute("evenements", evenements);
            List<TypeEvenement> typeEvenements = typeEvenementService.trouverTousTypeEvenements();
            Set<TypeEvenement> uniqueEventTypes = new HashSet<>(typeEvenements);
            model.addAttribute("uniqueEventTypes", uniqueEventTypes);
            model.addAttribute("profileOptions", List.of("Profil", "Amis", "Paramètres", "Déconnexion"));
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }
        return "home";
    }
}