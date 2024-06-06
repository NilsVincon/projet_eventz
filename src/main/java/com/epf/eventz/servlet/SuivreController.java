package com.epf.eventz.servlet;

import com.epf.eventz.exception.ServiceException;
import com.epf.eventz.model.Artiste;
import com.epf.eventz.model.Evenement;
import com.epf.eventz.model.Suivre;
import com.epf.eventz.model.Utilisateur;
import com.epf.eventz.service.SuivreService;
import com.epf.eventz.service.UtilisateurService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Slf4j
@Controller
@RequestMapping("/eventz/friend")
public class SuivreController {
    private final SuivreService suivreService;
    private final UtilisateurService utilisateurService;

    @Autowired
    public SuivreController(SuivreService suivreService, UtilisateurService utilisateurService) {
        this.suivreService = suivreService;
        this.utilisateurService = utilisateurService;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/add")
    public String addFriendPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<Utilisateur> utilisateurActuelOptional = null;
        List<Utilisateur> utilisateursAmis = new ArrayList<>();

        try {
            utilisateurActuelOptional = utilisateurService.trouverUtilisateurAvecname(username);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

        if (utilisateurActuelOptional.isPresent()) {
            Utilisateur utilisateurActuel = utilisateurActuelOptional.get();
            model.addAttribute("username", utilisateurActuel.getUsername());
            model.addAttribute("utilisateurActuel", utilisateurActuel);

            List<Utilisateur> utilisateurs = null;
            try {
                utilisateurs = utilisateurService.trouverTousUtilisateurs();
            } catch (ServiceException e) {
                throw new RuntimeException(e);
            }

            model.addAttribute("utilisateurs", utilisateurs);
            int nombreUtilisateurs = utilisateurs.size();
            int ageMoyen = (int) utilisateurs.stream()
                    .mapToInt(Utilisateur::getAge)
                    .average()
                    .orElse(0.0);

            model.addAttribute("nombreUtilisateurs", nombreUtilisateurs);
            model.addAttribute("ageMoyen", ageMoyen);

            for (Utilisateur utilisateur : utilisateurs) {
                if (suivreService.estAmis(utilisateurActuel, utilisateur)) {
                    utilisateursAmis.add(utilisateur);
                }
            }

            int nombreAmis = utilisateursAmis.size();
            model.addAttribute("nombreAmis", nombreAmis);
            model.addAttribute("amis", utilisateursAmis);

            // Ajout des abonnés et abonnements
            List<Utilisateur> abonnements = null;
            List<Utilisateur> abonnes = null;
            try {
                abonnements = utilisateurService.trouverAbonnementByUsername(username);
                abonnes = utilisateurService.trouverAbonnesByUsername(username);
            } catch (ServiceException e) {
                throw new RuntimeException(e);
            }
            int nbabonnements = abonnements.size();
            int nbabonne = abonnes.size();
            model.addAttribute("nbabonne", nbabonne);
            model.addAttribute("nbabonnements", nbabonnements);


            model.addAttribute("abonnements", abonnements);
            model.addAttribute("abonnes", abonnes);
        }
        return "add_friend";
    }


    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/list")
    public String ListFriendPage(Model model) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            Optional<Utilisateur> utilisateurActuelOptional = null;
            List<Utilisateur> utilisateursAmis = new ArrayList<>();
            try {
                utilisateurActuelOptional = utilisateurService.trouverUtilisateurAvecname(username);
            } catch (ServiceException e) {
                throw new RuntimeException(e);
            }
            if (utilisateurActuelOptional.isPresent()) {
                Utilisateur utilisateurActuel = utilisateurActuelOptional.get();
                List<Utilisateur> tousLesUtilisateurs = utilisateurService.trouverTousUtilisateurs();
                for (Utilisateur utilisateur : tousLesUtilisateurs) {
                    if (!utilisateur.equals(utilisateurActuel)) {
                        boolean isMutual = suivreService.estAmis(utilisateurActuel, utilisateur);
                        if(isMutual){
                            utilisateursAmis.add(utilisateur);
                        }
                    }
                }
            }
            model.addAttribute("listeAmis",utilisateursAmis);
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }
        return "list_friend";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/add")
    public void ajouter(@RequestParam("id") Long event_id, Model model, HttpServletResponse response) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            Optional<Utilisateur> utilisateursuiveurOptional = utilisateurService.trouverUtilisateurAvecname(username);
            Optional<Utilisateur> utilisateursuiviOptional = utilisateurService.trouverUtilisateurAvecId(event_id);
            if (utilisateursuiviOptional.isPresent() && utilisateursuiveurOptional.isPresent()) {
                Utilisateur utilisateursuivi = utilisateursuiviOptional.get();
                Utilisateur utilisateursuiveur = utilisateursuiveurOptional.get();
                log.info("NDQSOVNSOQVNO //// :"+utilisateursuiveur.equals(utilisateursuivi));
                if (!suivreService.existsParSuiveurEtSuivi(utilisateursuiveur, utilisateursuivi) && !utilisateursuiveur.equals(utilisateursuivi)) {
                    suivreService.creerSuivre(new Suivre(utilisateursuiveur, utilisateursuivi));
                    response.setHeader("Location", "/eventz/friend/add");
                    response.setStatus(HttpStatus.FOUND.value());
                } else {
                    response.setHeader("Location", "/error/addfriendimpossible");
                    response.setStatus(HttpStatus.FOUND.value());
                    //afficher une notif comme quoi on suit déjà cet utilisateur
                }
            }
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/remove")
    public void supprimer(@RequestParam("id") Long event_id, Model model, HttpServletResponse response) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            Optional<Utilisateur> utilisateursuiveurOptional = utilisateurService.trouverUtilisateurAvecname(username);
            Optional<Utilisateur> utilisateursuiviOptional = utilisateurService.trouverUtilisateurAvecId(event_id);
            if (utilisateursuiviOptional.isPresent() && utilisateursuiveurOptional.isPresent()) {
                Utilisateur utilisateursuivi = utilisateursuiviOptional.get();
                Utilisateur utilisateursuiveur = utilisateursuiveurOptional.get();
                if (suivreService.existsParSuiveurEtSuivi(utilisateursuiveur, utilisateursuivi)) {
                    suivreService.supprimerParSuiveurEtSuivi(utilisateursuiveur, utilisateursuivi);
                    response.setHeader("Location", "/eventz/friend/add");
                    response.setStatus(HttpStatus.FOUND.value());
                } else {
                    response.setHeader("Location", "/error/removefriendimpossible");
                    response.setStatus(HttpStatus.FOUND.value());
                    // afficher une notif comme quoi l'utilisateur ne suit pas cette personne
                }
            }
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/removeSubscription")
    public void supprimerAbonnement(@RequestParam("id") Long amiId, HttpServletResponse response) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            Optional<Utilisateur> utilisateurActuelOptional = utilisateurService.trouverUtilisateurAvecname(username);
            Optional<Utilisateur> utilisateurAbonnementOptional = utilisateurService.trouverUtilisateurAvecId(amiId);

            if (utilisateurActuelOptional.isPresent() && utilisateurAbonnementOptional.isPresent()) {
                Utilisateur utilisateurActuel = utilisateurActuelOptional.get();
                Utilisateur utilisateurAbonnement = utilisateurAbonnementOptional.get();

                suivreService.supprimerAbonnement(utilisateurActuel, utilisateurAbonnement);

                response.sendRedirect("/eventz/friend/add");
            } else {
                response.setStatus(HttpStatus.NOT_FOUND.value());
            }
        } catch (Exception e) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }


}
