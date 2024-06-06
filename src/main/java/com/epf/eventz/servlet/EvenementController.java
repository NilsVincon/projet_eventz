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
import java.io.IOException;
import java.util.List;
import java.util.Optional;


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
    private EmailService emailService;
    @Autowired
    private PerformeService performeService;

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

    @GetMapping("/afficherEvenement/{evenement}")
    public String afficherEvenement(@PathVariable("evenement") String evenement, Model model) {
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
            model.addAttribute("typeEvenements", typeEvenementsUnique);
            List<Artiste> artistes = artisteService.findAllArtistes();
            model.addAttribute("artistes", artistes);

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
                             @RequestParam("selectedArtistIdsInput") List<String> listIdArtists,
                             HttpServletResponse response) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            Optional<Utilisateur> organisateurOptional = utilisateurService.trouverUtilisateurAvecname(username);
            Utilisateur organisateur = null;
            if (organisateurOptional.isPresent()) {
                organisateur = organisateurOptional.get();
            }

            if (evenement.getPublic_evenement().equals(false)) {
                String subject = "Votre nouvel évènement privé : " + evenement.getNom_evenement();
                String lienEvenement = "http://localhost:8080/eventz/evenement/details?id=" + evenement.getIdEvenement();
                String organisateurEmailAdresse = organisateur.getEmail_utilisateur();
                String bodyEmail = "Bonjour " + organisateur.getPrenom_utilisateur() + " " + organisateur.getNom_utilisateur() + ",\n\n"
                        + "Vous venez de créer un évènement privé sur Eventz : " + evenement.getNom_evenement() + ".\n\n"
                        + "Voici le lien que vous pouvez partager avec vos amis pour qu'ils puissent rejoindre l'évènement :"
                        + lienEvenement +"\n\n"
                        + "Merci d'avoir choisi Eventz pour organiser vos évènements. Nous vous souhaitons une excellente soirée.\n\n"
                        + "Cordialement,\n\n"
                        + "L'équipe Eventz";
                emailService.sendEmail(organisateurEmailAdresse, subject, bodyEmail);
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

            for (String idString : listIdArtists) {
                Long id = Long.parseLong(idString);
                Optional<Artiste> artisteOptional = artisteService.findArtisteById(id);
                if (artisteOptional.isPresent()) {
                    Artiste artiste = artisteOptional.get();
                    Performe performance = new Performe(evenement, artiste);
                    performeService.creer(performance);
                }
            }

            participeService.addParticipe(new Participe(evenement, organisateur));
            response.setHeader("Location", "/eventz/home");
            response.setStatus(HttpStatus.FOUND.value());
        } catch (Exception e) {
            log.error("Erreur lors de l'ajout de l'evenement", e);
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }


    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/update")
    public String updatePage(@RequestParam("id") Long evenementId, Model model) throws ServiceException {

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
                try {

                    // Trouver l'événement par ID
                    Optional<Evenement> evenementOptional = evenementService.findEvenementById(evenementId);

                    if (evenementOptional.isPresent()) {
                        Evenement evenement = evenementOptional.get();
                        Utilisateur organisateur = evenement.getOrganisateur();

                        // Vérifier si l'utilisateur connecté est l'organisateur de l'événement
                        if (organisateur.equals(utilisateur)) {
                            model.addAttribute("event", evenement);
                            return "update_event";
                        }
                    }
                } catch (Exception e) {

                }

            }
        }
        return null ;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping(path = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void updateEvenement(@RequestParam("id") Long evenementId, @ModelAttribute Evenement evenement,@ModelAttribute Adresse adresse,
                                                                @ModelAttribute TypeEvenement typeEvenement,@RequestParam("pdpEvenementMultiPart") MultipartFile pdpEvenementMultiPart, HttpServletResponse response) throws IOException {
        try {
            log.info("Evenement :"+evenement.getNom_evenement());
            Optional<Evenement> evenementOptional = evenementService.findEvenementById(evenementId);
            if (evenementOptional.isPresent()) {
                Evenement existingevenement = evenementOptional.get();
                existingevenement.setNom_evenement(evenement.getNom_evenement());
                existingevenement.setDescription_evenement(evenement.getDescription_evenement());
                existingevenement.setDebut_evenement(evenement.getDebut_evenement());
                existingevenement.setFin_evenement(evenement.getFin_evenement());
                existingevenement.setPrix_evenement(evenement.getPrix_evenement());
                existingevenement.setNb_place_evenement(evenement.getNb_place_evenement());
                existingevenement.setPublic_evenement(evenement.getPublic_evenement());

                if (pdpEvenementMultiPart != null && !pdpEvenementMultiPart.isEmpty()) {
                    byte[] pdpBytes = pdpEvenementMultiPart.getBytes();
                    existingevenement.setPdpEvenement(pdpBytes);
                }

                if (typeEvenement != null && typeEvenement.getDescription_type_evenement() != null) {
                    typeEvenementService.creerTypeEvenement(typeEvenement);
                    existingevenement.setTypeEvenement(typeEvenement);
                }

                if (adresse != null && adresse.getRue_adresse() != null) {
                    adresseService.creerAdresse(adresse);
                    existingevenement.setAdresse(adresse);
                }


                // Appeler le service pour mettre à jour l'utilisateur
                evenementService.updateEvenement(evenementId, existingevenement);

                response.setHeader("Location", "/eventz/home");
                response.setStatus(HttpStatus.FOUND.value());

            } else {
                response.sendError(HttpStatus.NOT_FOUND.value(), "Utilisateur non trouvé");
            }
        } catch (Exception e) {
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Erreur lors de la mise à jour du profil: " + e.getMessage());
        }
    }



    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/delete")
    public String deletePage(@RequestParam("id") Long evenementId, Model model, HttpServletResponse response) throws ServiceException, IOException {
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
                try {

                    // Trouver l'événement par ID
                    Optional<Evenement> evenementOptional = evenementService.findEvenementById(evenementId);

                    if (evenementOptional.isPresent()) {
                        Evenement evenement = evenementOptional.get();
                        Utilisateur organisateur = evenement.getOrganisateur();

                        // Vérifier si l'utilisateur connecté est l'organisateur de l'événement
                        if (organisateur.equals(utilisateur)) {
                            model.addAttribute("event", evenement);
                            return "delete_event";
                        }
                    }
                } catch (Exception e) {
                    response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Erreur lors de la suppression de l'événement: " + e.getMessage());
                }

            }
        }
        return null ;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/delete")
    public void deleteEvenement(@RequestParam("idEvenement") Long evenementId, @ModelAttribute Evenement evenement, HttpServletResponse response) throws IOException {
        evenementService.deleteEvenement(evenementId);
        response.sendRedirect("/eventz/home");
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
                if (participeService.nbparticipants(evenement) + 1 < evenement.getNb_place_evenement()) {
                    Participe participe = new Participe(evenement, utilisateur);
                    participeService.addParticipe(participe);
                    response.setHeader("Location", "/eventz/evenement/details?id=" + event_id + "&fullevent=" + false);
                    response.setStatus(HttpStatus.FOUND.value());
                } else {
                    response.setHeader("Location", "/eventz/evenement/details?id=" + event_id + "&fullevent=" + true);
                    response.setStatus(HttpStatus.FOUND.value());
                }
            }
        } catch (ServiceException e) {
            throw new RuntimeException(e);
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
                List<Utilisateur> Utilisateurs = new ArrayList<>();
                for (Participe participe : evenement.getParticipes()) {
                    Utilisateurs.add(participe.getUtilisateur());
                }
                model.addAttribute("Utilisateurs", Utilisateurs);
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

