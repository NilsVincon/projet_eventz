package com.epf.eventz.servlet;

import com.epf.eventz.model.Utilisateur;
import com.epf.eventz.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@PreAuthorize("hasRole('ROLE_USER')")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    @Autowired
    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @GetMapping("/listeutilisateur")
    public String listUtilisateurs(Model model){
        try {
            List<Utilisateur> utilisateurs = utilisateurService.trouverTousUtilisateurs();
            model.addAttribute("utilisateurs", utilisateurs);
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }
        return "index";
    }

    @GetMapping("/profil/{utilisateurName}")
    public String profilUser(@PathVariable("utilisateurName")String utilisateurName, Model model){
        try {
            Optional<Utilisateur> utilisateur =  utilisateurService.trouverUtilisateurAvecname(utilisateurName);
            model.addAttribute("utilisateur", utilisateur);
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }

        return "profil";
    }
@PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/profil_utilisateur")
    public String listeAmis(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        try {
            Optional<Utilisateur> userOptional = utilisateurService.trouverUtilisateurAvecname(authentication.getName());
            List<Utilisateur> suiveurs = utilisateurService.trouverAbonnesByUsername(authentication.getName());
            List<Utilisateur> suivis = utilisateurService.trouverAbonnementByUsername(authentication.getName());
            if (userOptional.isPresent()) {
                Utilisateur user = userOptional.get();
                model.addAttribute("user", user);
                model.addAttribute("suiveurs", suiveurs);
                model.addAttribute("suivis", suivis);
            }
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }

        return "profil_utilisateur";
    }

    @GetMapping("/profil")
    public String profilUser(Model model){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            Optional<Utilisateur> userOptional = utilisateurService.trouverUtilisateurAvecname(username);
            if (userOptional.isPresent()) {
                Utilisateur user = userOptional.get();
                model.addAttribute("user", user);
            } else {
                // Gérer le cas où l'utilisateur n'est pas trouvé
            }
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }

        return "profil";
    }



    @PostMapping("/addutilisateur")
    public ResponseEntity<String> addUtilisateur(@RequestBody Utilisateur utilisateur){
        try {
            utilisateurService.creerUtilisateur(utilisateur);
            return ResponseEntity.ok("Utilisateur ajouté avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'ajout de l'utilisateur: " + e.getMessage());
        }
    }






    @DeleteMapping(path="/deleteutilisateur/{utilisateurId}")
    public ResponseEntity<String> deleteUtilisateur(@PathVariable("utilisateurId")Long utilisateurId){
        try {
            utilisateurService.supprimerUtilisateur(utilisateurId);
            return ResponseEntity.ok("Utilisateur supprimé avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'ajout de l'utilisateur: " + e.getMessage());
        }
    }


    @PutMapping(path="/modifyutilisateur/{utilisateurId}")
    public ResponseEntity<String> updateUtilisateur(@PathVariable("utilisateurId") Long utilisateurId, @RequestBody Utilisateur utilisateur){
        try {
            utilisateurService.modifierUtilisateur(utilisateurId, utilisateur);
            return ResponseEntity.ok("Utilisateur mis à jour avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la mise à jour de l'utilisateur: " + e.getMessage());
        }
    }

//    @PutMapping("/modifier/profil")
//    public ResponseEntity<String> updateProfile(@RequestBody Utilisateur utilisateur) {
//        try {
//            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//            String username = authentication.getName();
//            Optional<Utilisateur> userOptional = utilisateurService.trouverUtilisateurAvecname(username);
//
//            if (userOptional.isPresent()) {
//                Utilisateur existingUser = userOptional.get();
//                // Mettre à jour les champs du profil avec les nouvelles valeurs
//                existingUser.setPrenom_utilisateur(utilisateur.getPrenom_utilisateur());
//                existingUser.setNom_utilisateur(utilisateur.getNom_utilisateur()));
//                existingUser.setEmail_utilisateur(utilisateur.setEmail_utilisateur(););
//                existingUser.setNaissance_utilisateur(utilisateur.getNaissance_utilisateur());
//                existingUser.setSexe(utilisateur.getSexe());
//                existingUser.setDescription(utilisateur.getDescription());
//
//                // Appeler le service pour mettre à jour l'utilisateur
//                utilisateurService.modifierUtilisateur(existingUser.getId(), existingUser);
//
//                return ResponseEntity.ok("Profil mis à jour avec succès");
//            } else {
//                return ResponseEntity.notFound().build(); // Utilisateur non trouvé
//            }
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Erreur lors de la mise à jour du profil: " + e.getMessage());
//        }
//    }


}

