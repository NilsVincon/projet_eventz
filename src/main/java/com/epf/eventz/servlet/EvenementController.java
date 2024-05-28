package com.epf.eventz.servlet;

import com.epf.eventz.exception.ServiceException;
import com.epf.eventz.model.*;
import com.epf.eventz.service.*;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import com.epf.eventz.model.Artiste;
import com.epf.eventz.model.Evenement;
import com.epf.eventz.model.Participe;
import com.epf.eventz.model.Performe;
import com.epf.eventz.service.EvenementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
@RequestMapping("/eventz/evenement")
public class EvenementController {
    /*    private static final Logger logger = LoggerFactory.getLogger(JwtAuthentificationEntryPoint.class);*/

    private final EvenementService evenementService;
    private final AdresseService adresseService;
    private final StatutEvenementService statutEvenementService;
    private final TypeEvenementService typeEvenementService;
    private final UtilisateurService utilisateurService;
    private final ParticipeService participeService;

    @Autowired
    public EvenementController(EvenementService evenementService, AdresseService adresseService, StatutEvenementService statutEvenementService, TypeEvenementService typeEvenementService, UtilisateurService utilisateurService, ParticipeService participeService) {
        this.evenementService = evenementService;
        this.adresseService = adresseService;
        this.statutEvenementService = statutEvenementService;
        this.typeEvenementService = typeEvenementService;
        this.utilisateurService = utilisateurService;
        this.participeService = participeService;
    }

    @GetMapping("/details")
    public String afficherDetails(@RequestParam("id") Long event_id, Model model) {
        try {
            Optional<Evenement> evenementOptional = evenementService.findEvenementById(event_id);
            if (evenementOptional.isPresent()) {
                Evenement evenementactuel = evenementOptional.get();
                log.info(evenementactuel.toString());
                model.addAttribute("evenementactuel", evenementactuel);
            }
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        return "detail_evenement";

    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/add")
    public String loginPage() {
        return "add_event";
    }


    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/add")
    public void addEvenement(@ModelAttribute Evenement evenement, @ModelAttribute Adresse adresse, @ModelAttribute StatutEvenement statutEvenement, @ModelAttribute TypeEvenement typeEvenement, HttpServletResponse response) {
        try {
            typeEvenementService.creerTypeEvenement(typeEvenement);
            evenement.setTypeEvenement(typeEvenement);
            adresseService.creerAdresse(adresse);
            evenement.setAdresse(adresse);
            statutEvenementService.creerStatut(statutEvenement);
            evenement.setStatutEvenement(statutEvenement);
            evenementService.addEvenement(evenement);
            response.setHeader("Location", "/eventz/home");
            response.setStatus(HttpStatus.FOUND.value());
        } catch (Exception e) {
            log.error("Erreur lors de l'ajout de l'evenement");
        }
    }

    @DeleteMapping(path = "/deleteevenement/{evenementId}")
    public ResponseEntity<String> deleteEvenement(@PathVariable("evenementId") Long evenementId) {
        try {
            evenementService.deleteEvenement(evenementId);
            return ResponseEntity.ok("Evenement supprimé avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'ajout de l'evenement: " + e.getMessage());
        }
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/participate")
    public void participate(@RequestParam("event_id") Long event_id, HttpServletResponse response) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            Optional<Utilisateur> userOptional = utilisateurService.trouverUtilisateurAvecname(username);
            Optional<Evenement> evenementOptional = evenementService.findEvenementById(event_id);
            if (evenementOptional.isPresent() && userOptional.isPresent()) {
                Evenement evenement = evenementOptional.get();
                Utilisateur utilisateur = userOptional.get();
                Participe participe = new Participe(evenement, utilisateur);
                participeService.addParticipe(participe);
            }
            response.setHeader("Location", "/eventz/evenement/details?id=" + event_id);
            response.setStatus(HttpStatus.FOUND.value());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }


    @PutMapping(path = "/modifyevenement/{evenementId}")
    public ResponseEntity<String> updateEvenement(@PathVariable("evenementId") Long
                                                          evenementId, @RequestBody Evenement evenement) {
        try {
            evenementService.updateEvenement(evenementId, evenement);
            return ResponseEntity.ok("Evenement mis à jour avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la mise à jour de l'evenement: " + e.getMessage());
        }
    }

    @GetMapping(path = "/details/{evenementId}")
    public String detailsEvenement(@PathVariable Long evenementId, Model model){
        try {
            Optional<Evenement> evenementOptional = evenementService.findEvenementById(evenementId);
            if (evenementOptional.isPresent()) {
                Evenement evenement = evenementOptional.get();
                List<Artiste> artistes = new ArrayList<>();
                for (Performe performe : evenement.getPerformes()) {
                    artistes.add(performe.getArtiste());
                }
                model.addAttribute("evenement", evenement);
                model.addAttribute("artistes", artistes);
                return "events/event_details_description"; // Supposons que "profilartiste" est le nom de votre fichier HTML Thymeleaf
            } else {
                // Gérer le cas où l'artiste n'est pas trouvé, rediriger ou afficher un message d'erreur par exemple
                return "redirect:/error-500"; // Redirection vers une page d'erreur
            }
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }

}

