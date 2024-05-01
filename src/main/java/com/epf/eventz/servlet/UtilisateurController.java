package com.epf.eventz.servlet;

import com.epf.eventz.model.Utilisateur;
import com.epf.eventz.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
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

}

