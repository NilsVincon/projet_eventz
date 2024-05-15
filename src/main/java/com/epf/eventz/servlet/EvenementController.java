package com.epf.eventz.servlet;

import com.epf.eventz.exception.ServiceException;
import com.epf.eventz.model.*;
/*import com.epf.eventz.security.JwtAuthentificationEntryPoint;*/
import com.epf.eventz.service.*;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
@RequestMapping("/eventz/evenement")
public class EvenementController {
/*    private static final Logger logger = LoggerFactory.getLogger(JwtAuthentificationEntryPoint.class);*/

    private final EvenementService evenementService;
    private final AdresseService adresseService;
    private final StatutEvenementService statutEvenementService;
    private final TypeEvenementService typeEvenementService;

    @Autowired
    public EvenementController(EvenementService evenementService,AdresseService adresseService,StatutEvenementService statutEvenementService,TypeEvenementService typeEvenementService) {
        this.evenementService = evenementService;
        this.adresseService=adresseService;
        this.statutEvenementService=statutEvenementService;
        this.typeEvenementService=typeEvenementService;
    }

    @GetMapping("/details")
    public String afficherDetails(@RequestParam("id") Long event_id,Model model){
        try {
            Optional<Evenement> evenementOptional = evenementService.findEvenementById(event_id);
            if (evenementOptional.isPresent()){
                Evenement evenementactuel = evenementOptional.get();
                log.info(evenementactuel.toString());
                model.addAttribute("evenementactuel", evenementactuel);
            }
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        return "detail_evenement";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/add")
    public String loginPage() {
        return "add_event";
    }


    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/add")
        public void addEvenement(@ModelAttribute Evenement evenement, @ModelAttribute Adresse adresse, @ModelAttribute StatutEvenement statutEvenement, @ModelAttribute TypeEvenement typeEvenement, HttpServletResponse response){
        try {
            typeEvenementService.creerTypeEvenement(typeEvenement);
            evenement.setTypeEvenement(typeEvenement);
            adresseService.creerAdresse(adresse);
            evenement.setAdresse(adresse);
            statutEvenementService.creerStatut(statutEvenement);
            evenement.setStatutEvenement(statutEvenement);
            evenementService.addEvenement(evenement);
            response.setHeader("Location", "/eventz/home");
            response.setStatus(HttpStatus.FOUND.value());
        } catch (Exception e) {
            log.error("Erreur lors de l'ajout de l'evenement");
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

