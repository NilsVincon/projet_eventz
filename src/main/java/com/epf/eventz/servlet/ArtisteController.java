package com.epf.eventz.servlet;

import com.epf.eventz.exception.ServiceException;
import com.epf.eventz.model.Adresse;
import com.epf.eventz.model.Artiste;
import com.epf.eventz.model.Jouer;
import com.epf.eventz.model.Utilisateur;
import com.epf.eventz.service.ArtisteService;
import com.epf.eventz.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/eventz/artiste")
public class ArtisteController {

    private final ArtisteService artisteService;

    @Autowired
    public ArtisteController(ArtisteService artisteService) {
        this.artisteService = artisteService;
    }

    @GetMapping("/admin/listeartiste")
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
    @GetMapping("/artiste/{id}")
    public String listAdresses(@PathVariable Long id, Model model) throws ServiceException {
        Optional<Artiste> artisteOptional = artisteService.findArtisteById(id);
        if (artisteOptional.isPresent()) {
            Artiste artiste = artisteOptional.get();
            StringBuilder typemusique = artisteService.afficherTypeMusique(artiste);
            model.addAttribute("typeMusique", typemusique);
            model.addAttribute("artiste", artiste);
            return "profilartiste"; // Supposons que "profilartiste" est le nom de votre fichier HTML Thymeleaf
        } else {
            // Gérer le cas où l'artiste n'est pas trouvé, rediriger ou afficher un message d'erreur par exemple
            return "redirect:/error"; // Redirection vers une page d'erreur
        }
    }


    @GetMapping("/profile-image/{userId}")
    public ResponseEntity<byte[]> getProfileImage(@PathVariable Long userId) {
        try {
            Optional<Artiste> artisteOptional = artisteService.findArtisteById(userId);
            if (artisteOptional.isPresent()) {
                Artiste artiste = artisteOptional.get();
                if (artiste.getPdpArtiste() != null) {
                    return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(artiste.getPdpArtiste());
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

