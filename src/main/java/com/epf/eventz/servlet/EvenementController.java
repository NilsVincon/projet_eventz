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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequestMapping("/eventz/evenement")
public class EvenementController {
    /*    private static final Logger logger = LoggerFactory.getLogger(JwtAuthentificationEntryPoint.class);*/
    @Autowired
    private EvenementService evenementService;
    @Autowired
    private AdresseService adresseService;
    @Autowired
    private StatutEvenementService statutEvenementService;
    @Autowired
    private TypeEvenementService typeEvenementService;
    @Autowired
    private UtilisateurService utilisateurService;
    @Autowired
    private ParticipeService participeService;

    @Autowired
    private ArtisteService artisteService;

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

    @GetMapping("/afficherEvenement")
    public String afficherEvenement(@RequestParam("evenement") String evenement, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.getName().equals("anonymousUser")) {
            Optional<Utilisateur> utilisateurOptional = null;
            try {
                utilisateurOptional = utilisateurService.trouverUtilisateurAvecname(authentication.getName());
            } catch (ServiceException e) {
                throw new RuntimeException(e);
            }
            if (utilisateurOptional.isPresent()) {
                Utilisateur utilisateur = utilisateurOptional.get();
                List<Evenement> mesevenements = participeService.findEvenementsByUtilisateur(utilisateur);
                List<Evenement> mesevenementsorganise = evenementService.findEvenementsByOrganisateur(utilisateur);
                if (evenement != null && !evenement.isEmpty()) {
                    if (evenement.equals("avenir")) {
                        mesevenements = mesevenements.stream()
                                .filter(e -> e.getDebut_evenement().isAfter(LocalDate.now()))
                                .collect(Collectors.toList());
                    }
                    if (evenement.equals("organise")) {
                        mesevenements = evenementService.findEvenementsByOrganisateur(utilisateur);
                    } else if (evenement.equals("passe")) {
                        mesevenements = mesevenements.stream()
                                .filter(e -> e.getDebut_evenement().isBefore(LocalDate.now()))
                                .collect(Collectors.toList());
                    } else {

                    }
                }
                model.addAttribute("evenementorganise", mesevenementsorganise);
                model.addAttribute("mesevenements", mesevenements);
            }
        }
        return "events/mes_evenements";
    }

    @GetMapping("/list")
    public String afficherMesEvenements(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.getName().equals("anonymousUser")) {
            Optional<Utilisateur> utilisateurOptional = null;
            try {
                utilisateurOptional = utilisateurService.trouverUtilisateurAvecname(authentication.getName());
            } catch (ServiceException e) {
                throw new RuntimeException(e);
            }
            if (utilisateurOptional.isPresent()) {
                Utilisateur utilisateur = utilisateurOptional.get();

                List<Evenement> evenementsParticipe = participeService.findEvenementsByUtilisateur(utilisateur);
                List<Evenement> evenementsOrganises = evenementService.findEvenementsByOrganisateur(utilisateur);
                model.addAttribute("evenementorganise", evenementsOrganises);
                Set<Evenement> mesEvenements = new HashSet<>(evenementsParticipe);
                mesEvenements.addAll(evenementsOrganises);
                model.addAttribute("mesevenements", new ArrayList<>(mesEvenements));
            }
        }
        return "events/mes_evenements";

    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/add")
    public String loginPage(Model model) {
        try {
            List<TypeEvenement> typeEvenements = typeEvenementService.trouverTousTypeEvenements();
            Set<TypeEvenement> typeEvenementsUnique = new HashSet<>(typeEvenements);
            model.addAttribute("typeEvenements",typeEvenementsUnique);
            List<Artiste> artistes = artisteService.findAllArtistes();
            model.addAttribute("artistes",artistes);

        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

        return "add_event";
    }


    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/add")
    public void addEvenement(@ModelAttribute Evenement evenement,
                             @ModelAttribute Adresse adresse,
                             @ModelAttribute StatutEvenement statutEvenement,
                             @RequestParam("pdpEvenementMultiPart") MultipartFile pdpEvenementMultiPart,
                             @ModelAttribute TypeEvenement typeEvenement,
                             HttpServletResponse response) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            Optional<Utilisateur> organisateurOptional = utilisateurService.trouverUtilisateurAvecname(username);
            Utilisateur organisateur = null;
            if (organisateurOptional.isPresent()) {
                organisateur = organisateurOptional.get();
            }

            if (typeEvenement != null && typeEvenement.getDescription_type_evenement() != null) {
                typeEvenementService.creerTypeEvenement(typeEvenement);
                evenement.setTypeEvenement(typeEvenement);
            }

            if (adresse != null && adresse.getRue_adresse() != null) {
                adresseService.creerAdresse(adresse);
                evenement.setAdresse(adresse);
            }

            if (statutEvenement != null && statutEvenement.getDescription_statut_evenement() != null) {
                statutEvenementService.creerStatut(statutEvenement);
                evenement.setStatutEvenement(statutEvenement);
            }

            evenement.setOrganisateur(organisateur);

            if (pdpEvenementMultiPart != null && !pdpEvenementMultiPart.isEmpty()) {
                byte[] pdpBytes = pdpEvenementMultiPart.getBytes();
                evenement.setPdpEvenement(pdpBytes);
            }

            evenementService.addEvenement(evenement);
            participeService.addParticipe(new Participe(evenement, organisateur));
            response.setHeader("Location", "/eventz/home");
            response.setStatus(HttpStatus.FOUND.value());
        } catch (Exception e) {
            log.error("Erreur lors de l'ajout de l'evenement", e);
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
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
    public String detailsEvenement(@PathVariable Long evenementId, Model model) {
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

    @GetMapping("/image/{eventId}")
    public ResponseEntity<byte[]> getProfileImage(@PathVariable Long eventId) {
        try {
            Optional<Evenement> evenementOptional = evenementService.findEvenementById(eventId);
            if (evenementOptional.isPresent()) {
                Evenement evenement = evenementOptional.get();
                if (evenement.getPdpEvenement() != null) {
                    return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(evenement.getPdpEvenement());
                } else {
                    return ResponseEntity.notFound().build();
                }
            }
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}

