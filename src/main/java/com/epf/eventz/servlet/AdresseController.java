package com.epf.eventz.servlet;

import com.epf.eventz.model.Adresse;
import com.epf.eventz.service.AdresseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
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
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }

        return "index";
    }

}

