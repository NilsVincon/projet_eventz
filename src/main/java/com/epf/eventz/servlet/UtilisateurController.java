package com.epf.eventz.servlet;

import com.epf.eventz.exception.ServiceException;
import com.epf.eventz.model.*;
import com.epf.eventz.service.EvenementService;
import com.epf.eventz.service.SuivreService;
import com.epf.eventz.service.UtilisateurService;
import jakarta.servlet.http.HttpServletResponse;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/eventz/user")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;
    private final EvenementService evenementService;
    private final SuivreService suivreService;

    @Autowired
    public UtilisateurController(UtilisateurService utilisateurService, EvenementService evenementService, SuivreService suivreService) {
        this.utilisateurService = utilisateurService;
        this.evenementService=evenementService;
        this.suivreService=suivreService;
    }

    @GetMapping("/listeutilisateur")
    public String listUtilisateurs(Model model) {
        try {
            List<Utilisateur> utilisateurs = utilisateurService.trouverTousUtilisateurs();
            model.addAttribute("utilisateurs", utilisateurs);
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }
        return "index";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/profil/{utilisateurName}")
    public String profilUser(@PathVariable("utilisateurName") String utilisateurName, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        try {
            Optional<Utilisateur> utilisateurOptional = utilisateurService.trouverUtilisateurAvecname(utilisateurName);
            if (utilisateurOptional.isPresent())  {
                Utilisateur utilisateur = utilisateurOptional.get();
                List<Evenement> evenements = evenementService.findByOrganisateur(utilisateur);
                List<Artiste> artistes=utilisateurService.trouverArtistesByUsername(utilisateur.getUsername());
                Map<Boolean, List<Evenement>> eventz = evenementService.separerEvenementsParDate(evenements);
                List<Utilisateur> suiveurs = utilisateurService.trouverAbonnesByUsername(utilisateur.getUsername());
                List<Utilisateur> suivis = utilisateurService.trouverAbonnementByUsername(utilisateur.getUsername());
                List<Utilisateur> communs = suiveurs.stream()
                        .filter(utilisateurService.trouverAbonnementByUsername(authentication.getName())::contains)
                        .toList();
                model.addAttribute("suiveurs", suiveurs);
                model.addAttribute("suivis", suivis);
                model.addAttribute("communs", communs);
                model.addAttribute("evenements", evenements);
                model.addAttribute("eventPasse", eventz.get(false));
                model.addAttribute("eventAVenir", eventz.get(true));
                model.addAttribute("artistes", artistes);
                if (authentication.getName().equals(utilisateurName)){
                    model.addAttribute("user", utilisateur);
                    return "utilisateur/profil_utilisateur";
                }
                Optional<Utilisateur> userOptional = utilisateurService.trouverUtilisateurAvecname(authentication.getName());

                if (userOptional.isPresent()) {
                    Utilisateur user = userOptional.get();
                    boolean isFollowing = suivreService.existsBySuiveurAndSuivi(user, utilisateur);
                    model.addAttribute("boutonSuivre", isFollowing ? "Suivi" : "Suivre");
                } else {
                    model.addAttribute("boutonSuivre", "Suivre");
                }
                model.addAttribute("utilisateur", utilisateur);
            }
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }

        return "ami/profil_ami";
    }


    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/profil")
    public String listeAmis(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        try {
            Optional<Utilisateur> userOptional = utilisateurService.trouverUtilisateurAvecname(authentication.getName());
            List<Utilisateur> suiveurs = utilisateurService.trouverAbonnesByUsername(authentication.getName());
            List<Utilisateur> suivis = utilisateurService.trouverAbonnementByUsername(authentication.getName());
            List<Artiste> artistes=utilisateurService.trouverArtistesByUsername(authentication.getName());
            if (userOptional.isPresent())  {
                Utilisateur user = userOptional.get();
                model.addAttribute("user", user);
                model.addAttribute("suiveurs", suiveurs);
                model.addAttribute("suivis", suivis);
                model.addAttribute("artistes", artistes);
            }
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }

        return "utilisateur/profil_utilisateur";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/add")
    public ResponseEntity<String> addUtilisateur(@RequestBody Utilisateur utilisateur) {
        try {
            utilisateurService.creerUtilisateur(utilisateur);
            return ResponseEntity.ok("Utilisateur ajouté avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'ajout de l'utilisateur: " + e.getMessage());
        }
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping(path = "/deleteutilisateur/{utilisateurId}")
    public ResponseEntity<String> deleteUtilisateur(@PathVariable("utilisateurId") Long utilisateurId) {
        try {
            utilisateurService.supprimerUtilisateur(utilisateurId);
            return ResponseEntity.ok("Utilisateur supprimé avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'ajout de l'utilisateur: " + e.getMessage());
        }
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/modifier/profil")
    public void updateProfile(
            @ModelAttribute Utilisateur utilisateur,
            @RequestParam("pdpUtilisateurInput") MultipartFile pdpUtilisateurInput,
            HttpServletResponse response,
            RedirectAttributes redirectAttributes) throws IOException {

        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            Optional<Utilisateur> userOptional = utilisateurService.trouverUtilisateurAvecname(username);

            if (userOptional.isPresent()) {
                Utilisateur existingUser = userOptional.get();
                existingUser.setPrenom_utilisateur(utilisateur.getPrenom_utilisateur());
                existingUser.setNom_utilisateur(utilisateur.getNom_utilisateur());
                existingUser.setNaissance_utilisateur(utilisateur.getNaissance_utilisateur());
                existingUser.setSexe_utilisateur(utilisateur.getSexe_utilisateur());
                existingUser.setDescription_utilisateur(utilisateur.getDescription_utilisateur());
                existingUser.setUsername(utilisateur.getUsername());
                if (!pdpUtilisateurInput.isEmpty()) {
                    byte[] pdpBytes = pdpUtilisateurInput.getBytes();
                    existingUser.setPdpUtilisateur(pdpBytes);
                }

                utilisateurService.modifierUtilisateur((long) existingUser.getId_utilisateur(), existingUser);

                response.setHeader("Location", "/eventz/user/profil");
                response.setStatus(HttpStatus.FOUND.value());

            } else {
                response.sendError(HttpStatus.NOT_FOUND.value(), "Utilisateur non trouvé");
            }
        } catch (Exception e) {
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Erreur lors de la mise à jour du profil: " + e.getMessage());
        }
    }


    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/modifier/profil")
    public String modifierProfilUser() throws ServiceException {
        return "utilisateur/modifierProfil";
    }

    @GetMapping("/profile-imagechat/{sendername}")
    public ResponseEntity<byte[]> getProfileImageChat(@PathVariable String sendername) {
        try {
            Optional<Utilisateur> utilisateurOptional = utilisateurService.trouverUtilisateurAvecname(sendername);
            if (utilisateurOptional.isPresent()) {
                Utilisateur utilisateur = utilisateurOptional.get();
                if (utilisateur.getPdpUtilisateur() != null) {
                    return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(utilisateur.getPdpUtilisateur());
                } else {
                    return ResponseEntity.notFound().build();
                }
            }
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @GetMapping("/profile-image/{userId}")
    public ResponseEntity<byte[]> getProfileImage(@PathVariable Long userId) {
        try {
            Optional<Utilisateur> utilisateurOptional = utilisateurService.trouverUtilisateurAvecId(userId);
            if (utilisateurOptional.isPresent()) {
                Utilisateur utilisateur = utilisateurOptional.get();
                if (utilisateur.getPdpUtilisateur() != null) {
                    return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(utilisateur.getPdpUtilisateur());
                } else {
                    return ResponseEntity.notFound().build();
                }
            }
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @PostMapping("/follow")
    public String suivreUser(@RequestParam("id_utilisateur") Long id_utilisateur, @ModelAttribute Suivre
            suivre) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Optional<Utilisateur> utilisateurOptional = utilisateurService.trouverUtilisateurAvecId(id_utilisateur);
            Optional<Utilisateur> userOptional = utilisateurService.trouverUtilisateurAvecname(authentication.getName());

            if (utilisateurOptional.isPresent() && userOptional.isPresent()) {
                Utilisateur utilisateur = utilisateurOptional.get();
                Utilisateur user = userOptional.get();
                if (!suivreService.existsBySuiveurAndSuivi(user, utilisateur)) {
                    suivre.setSuivi(utilisateur);
                    suivre.setSuiveur(user);
                    suivreService.creerSuivre(suivre);
                } else {
                    suivreService.supprimerParSuiveurEtSuivi(user, utilisateur);
                }
                return "redirect:/eventz/user/profil/" + utilisateur.getUsername();
            } else {
                return "redirect:/error";
            }
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }



}

