package com.epf.eventz.servlet;

import com.epf.eventz.model.Adresse;
import com.epf.eventz.service.AdresseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/adresse")
public class AdresseController {

    private final AdresseService adresseService;

    @Autowired
    public AdresseController(AdresseService adresseService) {
        this.adresseService = adresseService;
    }

    @GetMapping("/listeadresse")

    public String listAdresses(Model model){
        try {
            List<Adresse> adresses = adresseService.trouverToutesAdresses();
            model.addAttribute("adresses", adresses);
            return "admin/listeadresse";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Une erreur s'est produite lors de la récupération des adresses.");
            return "error";
        }
    }
    @PostMapping("/addadresse")
    public ResponseEntity<String> addAdresse(@RequestBody Adresse adresse){
        try {
            adresseService.creerAdresse(adresse);
            return ResponseEntity.ok("Adresse ajouté avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'ajout de l'adresse: " + e.getMessage());
        }
    }




    @DeleteMapping(path="/deleteadresse/{adresseId}")
    public ResponseEntity<String> deleteAdresse(@PathVariable("adresseId")Long adresseId){
        try {
            adresseService.supprimerAdresse(adresseId);
            return ResponseEntity.ok("Adresse supprimé avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'ajout de l'adresse: " + e.getMessage());
        }
    }


    @PutMapping(path="/modifyadresse/{adresseId}")
    public ResponseEntity<String> updateAdresse(@PathVariable("adresseId") Long adresseId, @RequestBody Adresse adresse){
        try {
            adresseService.modifierAdresse(adresseId, adresse);
            return ResponseEntity.ok("Adresse mis à jour avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la mise à jour de l'adresse: " + e.getMessage());
        }
    }

}
