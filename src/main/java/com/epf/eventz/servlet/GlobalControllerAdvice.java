package com.epf.eventz.servlet;

import com.epf.eventz.exception.ServiceException;
import com.epf.eventz.model.Utilisateur;
import com.epf.eventz.service.UtilisateurService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

@ControllerAdvice
public class GlobalControllerAdvice {

    private final UtilisateurService utilisateurService;

    public GlobalControllerAdvice(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

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
}
