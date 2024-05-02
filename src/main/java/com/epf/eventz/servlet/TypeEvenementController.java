package com.epf.eventz.servlet;


import com.epf.eventz.model.TypeEvenement;
import com.epf.eventz.service.TypeEvenementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TypeEvenementController {

    private final TypeEvenementService typeEvenementService;

    @Autowired
    public TypeEvenementController(TypeEvenementService typeEvenementService){ this.typeEvenementService = typeEvenementService;}

    @GetMapping("/listetypeevenement")
    public String listTypeEvenement(Model model) {
        try {
            List<TypeEvenement> typeEvenement = typeEvenementService.trouverTousTypeEvenements();
            model.addAttribute("typeevenements", typeEvenement);
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }

        return "admin/listetypeevenement";
    }

    @PostMapping("/addtypeevenement")
    public ResponseEntity<String> addTypeevenement(@RequestBody TypeEvenement typeevenement){
        try {
            typeEvenementService.creerTypeEvenement(typeevenement);
            return ResponseEntity.ok("Typeevenement ajouté avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'ajout de l'typeevenement: " + e.getMessage());
        }
    }

    @DeleteMapping(path="/deletetypeevenement/{typeevenementId}")
    public ResponseEntity<String> deleteTypeevenement(@PathVariable("typeevenementId")Long typeevenementId){
        try {
            typeEvenementService.supprimerTypeEvenement(typeevenementId);
            return ResponseEntity.ok("Typeevenement supprimé avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'ajout de l'typeevenement: " + e.getMessage());
        }
    }


    @PutMapping(path="/modifytypeevenement/{typeevenementId}")
    public ResponseEntity<String> updateTypeevenement(@PathVariable("typeevenementId") Long typeevenementId, @RequestBody TypeEvenement typeevenement){
        try {
            typeEvenementService.updateTypeevenement(typeevenementId, typeevenement);
            return ResponseEntity.ok("Typeevenement mis à jour avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la mise à jour de l'typeevenement: " + e.getMessage());
        }
    }
    
}

