package com.epf.eventz.servlet;

import com.epf.eventz.model.Evenement;
import com.epf.eventz.service.EvenementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EvenementController {

    private final EvenementService evenementService;

    @Autowired
    public EvenementController(EvenementService evenementService) {
        this.evenementService = evenementService;
    }

    @GetMapping("/listeevenement")
    public String listEvenements(Model model){
        try {
            List<Evenement> evenements = evenementService.findAllEvenements();
            model.addAttribute("evenements", evenements);
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }

        return "listeEvenement";
    }

    @PostMapping("/addevenement")
    public ResponseEntity<String> addEvenement(@RequestBody Evenement evenement){
        try {
            evenementService.addEvenement(evenement);
            return ResponseEntity.ok("Evenement ajouté avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'ajout de l'evenement: " + e.getMessage());
        }
    }




    @DeleteMapping(path="/deleteevenement/{evenementId}")
    public ResponseEntity<String> deleteEvenement(@PathVariable("evenementId")Long evenementId){
        try {
            evenementService.deleteEvenement(evenementId);
            return ResponseEntity.ok("Evenement supprimé avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'ajout de l'evenement: " + e.getMessage());
        }
    }


    @PutMapping(path="/modifyevenement/{evenementId}")
    public ResponseEntity<String> updateEvenement(@PathVariable("evenementId") Long evenementId, @RequestBody Evenement evenement){
        try {
            evenementService.updateEvenement(evenementId, evenement);
            return ResponseEntity.ok("Evenement mis à jour avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la mise à jour de l'evenement: " + e.getMessage());
        }
    }

}

