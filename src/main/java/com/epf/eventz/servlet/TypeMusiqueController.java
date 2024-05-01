package com.epf.eventz.servlet;

import com.epf.eventz.model.Adresse;
import com.epf.eventz.model.TypeMusique;
import com.epf.eventz.service.AdresseService;
import com.epf.eventz.service.TypeMusiqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TypeMusiqueController {

    private final TypeMusiqueService typeMusiqueService;

    @Autowired
    public TypeMusiqueController(TypeMusiqueService typeMusiqueService) {
        this.typeMusiqueService = typeMusiqueService;
    }

    @GetMapping("/listetypemusique")
    public String listTypeMusique(Model model){
        try {
            List<TypeMusique> typeMusiques = typeMusiqueService.findAllTypeMusiques()   ;
            model.addAttribute("typemusiques", typeMusiques);

        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }

        return "listetypemusique";
    }

    @PostMapping("/addtypemusique")
    public ResponseEntity<String> addTypemusique(@RequestBody TypeMusique typemusique){
        try {
            typeMusiqueService.addTypeMusique(typemusique);
            return ResponseEntity.ok("Typemusique ajouté avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'ajout de l'typemusique: " + e.getMessage());
        }
    }




    @DeleteMapping(path="/deletetypemusique/{typemusiqueId}")
    public ResponseEntity<String> deleteTypemusique(@PathVariable("typemusiqueId")Long typemusiqueId){
        try {
            typeMusiqueService.deleteTypeMusique(typemusiqueId);
            return ResponseEntity.ok("Typemusique supprimé avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'ajout de l'typemusique: " + e.getMessage());
        }
    }


    @PutMapping(path="/modifytypemusique/{typemusiqueId}")
    public ResponseEntity<String> updateTypemusique(@PathVariable("typemusiqueId") Long typemusiqueId, @RequestBody TypeMusique typemusique){
        try {
            typeMusiqueService.updateTypemusique(typemusiqueId, typemusique);
            return ResponseEntity.ok("Typemusique mis à jour avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la mise à jour de l'typemusique: " + e.getMessage());
        }
    }
    
}

