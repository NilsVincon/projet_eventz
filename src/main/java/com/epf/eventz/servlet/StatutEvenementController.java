package com.epf.eventz.servlet;

import com.epf.eventz.model.StatutEvenement;
import com.epf.eventz.service.StatutEvenementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StatutEvenementController {

    private final StatutEvenementService statutEvenementService;

    @Autowired
    public StatutEvenementController(StatutEvenementService statutEvenementService) {
        this.statutEvenementService = statutEvenementService;
    }

    @GetMapping("/listestatutevenement")
    public String listStatutEvenement(Model model) {
        try {
            List<StatutEvenement> statutEvenements = statutEvenementService.trouverTousStatuts();
            model.addAttribute("statutEvenements", statutEvenements);
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }

        return "listestatutevenement";
    }

    @PostMapping("/addstatutevenement")
    public ResponseEntity<String> addStatutEvenement(@RequestBody StatutEvenement statutEvenement) {
        try {
            statutEvenementService.creerStatut(statutEvenement);
            return ResponseEntity.ok("StatutEvenement ajouté avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'ajout du StatutEvenement: " + e.getMessage());
        }
    }

    @DeleteMapping(path = "/deletestatutevenement/{statutEvenementId}")
    public ResponseEntity<String> deleteStatutEvenement(@PathVariable("statutEvenementId") Long statutEvenementId) {
        try {
            statutEvenementService.supprimerStatut(statutEvenementId);
            return ResponseEntity.ok("StatutEvenement supprimé avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la suppression du StatutEvenement: " + e.getMessage());
        }
    }

    @PutMapping(path = "/modifystatutevenement/{statutEvenementId}")
    public ResponseEntity<String> updateStatutEvenement(@PathVariable("statutEvenementId") Long statutEvenementId, @RequestBody StatutEvenement statutEvenement) {
        try {
            statutEvenementService.updateStatutevenement(statutEvenementId, statutEvenement);
            return ResponseEntity.ok("StatutEvenement mis à jour avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la mise à jour du StatutEvenement: " + e.getMessage());
        }
    }
}

