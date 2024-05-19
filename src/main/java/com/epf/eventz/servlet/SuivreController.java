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
    public String loginPage(Model model) {
        try {
            List<Utilisateur> utilisateurs = utilisateurService.trouverTousUtilisateurs();
            model.addAttribute("utilisateurs", utilisateurs);
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }
        return "add_friend";
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
                    response.setHeader("Location", "/eventz/home");
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
}
