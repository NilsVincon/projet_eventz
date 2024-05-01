package com.epf.eventz.servlet;

import com.epf.eventz.model.Artiste;
import com.epf.eventz.service.ArtisteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ArtisteController {

    private final ArtisteService artisteService;

    @Autowired
    public ArtisteController(ArtisteService artisteService) {
        this.artisteService = artisteService;
    }

    @GetMapping("/listeartiste")
    public String listArtistes(Model model){
        try {
            List<Artiste> artistes = artisteService.findAllArtistes();
            model.addAttribute("artistes", artistes);
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }

        return "listeArtiste";
    }
    @PostMapping("/addartiste")
    public ResponseEntity<String> addArtiste(@RequestBody Artiste artiste){
        try {
            artisteService.addArtiste(artiste);
            return ResponseEntity.ok("Artiste ajouté avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'ajout de l'artiste: " + e.getMessage());
        }
    }




    @DeleteMapping(path="/deleteartiste/{artisteId}")
    public ResponseEntity<String> deleteArtiste(@PathVariable("artisteId")Long artisteId){
        try {
            artisteService.deleteArtiste(artisteId);
            return ResponseEntity.ok("Artiste supprimé avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'ajout de l'artiste: " + e.getMessage());
        }
    }


    @PutMapping(path="/modifyartiste/{artisteId}")
    public ResponseEntity<String> updateArtiste(@PathVariable("artisteId") Long artisteId, @RequestBody Artiste artiste){
        try {
            artisteService.updateArtiste(artisteId, artiste);
            return ResponseEntity.ok("Artiste mis à jour avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la mise à jour de l'artiste: " + e.getMessage());
        }
    }


}

