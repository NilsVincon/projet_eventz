package com.epf.eventz.servlet;

import com.epf.eventz.model.Evenement;
import com.epf.eventz.service.EvenementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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
    //@PostMapping
    //public void addEvenement(@RequestBody Evenement evenement){
    //    evenementService.addEvenement(evenement);
    //}

}

