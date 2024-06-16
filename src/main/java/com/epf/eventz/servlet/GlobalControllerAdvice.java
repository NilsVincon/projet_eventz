package com.epf.eventz.servlet;

import com.epf.eventz.exception.ServiceException;
import com.epf.eventz.model.Artiste;
import com.epf.eventz.model.Evenement;
import com.epf.eventz.model.Utilisateur;
import com.epf.eventz.service.ArtisteService;
import com.epf.eventz.service.EvenementService;
import com.epf.eventz.service.ParticipeService;
import com.epf.eventz.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

@ControllerAdvice
public class GlobalControllerAdvice {

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private EvenementService evenementService;

    @Autowired
    private ArtisteService artisteService;

    @Autowired
    private  ParticipeService participeService;


    @ModelAttribute
    public void addUserToModel(@AuthenticationPrincipal UserDetails currentUser, Model model) {
        boolean connecte=false;
        if (currentUser != null) {
            connecte=true;
            Optional<Utilisateur> userOptional = null;
            try {
                userOptional = utilisateurService.trouverUtilisateurAvecname(currentUser.getUsername());
            } catch (ServiceException e) {
                throw new RuntimeException(e);
            }
            userOptional.ifPresent(utilisateur -> model.addAttribute("user", utilisateur));
        }
        model.addAttribute("connecte", connecte);
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.getName().equals("anonymousUser")) {
            Optional<Utilisateur> utilisateurOptional = null;
            try {
                utilisateurOptional = utilisateurService.trouverUtilisateurAvecname(authentication.getName());
            } catch (ServiceException e) {
                throw new RuntimeException(e);
            }
            if(utilisateurOptional.isPresent()){
                Utilisateur utilisateur = utilisateurOptional.get();
                List<Evenement> evenements = participeService.findEvenementsByUtilisateur(utilisateur);
                model.addAttribute("evenementsparticipe", evenements);
                model.addAttribute("username", utilisateur.getUsername());
            }
        }
    }

    @ModelAttribute
    public void addSearch(Model model) throws ServiceException {
        List<Evenement> listEvenementSearchNavbar = evenementService.findAllEvenements();
        List<Artiste> listArtisteearchNavbar = artisteService.findAllArtistes();
        List<Utilisateur> listeUtilisateurSearchNavbar = utilisateurService.trouverTousUtilisateurs();
        model.addAttribute("listEvenementSearchNavbar", listEvenementSearchNavbar);
        model.addAttribute("listArtisteSearchNavbar", listArtisteearchNavbar);
        model.addAttribute("listUtilisateurSearchNavbar", listeUtilisateurSearchNavbar );
    }

}
